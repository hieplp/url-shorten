package com.hieplp.url.gateway.service.impl;

import com.google.inject.Inject;
import com.hieplp.url.common.constants.auth.HeaderKey;
import com.hieplp.url.common.constants.auth.TokenType;
import com.hieplp.url.common.constants.discovery.DiscoveryMetadata;
import com.hieplp.url.common.constants.discovery.DiscoveryServiceName;
import com.hieplp.url.common.exception.auth.UnauthorizedException;
import com.hieplp.url.common.exception.data.NotFoundException;
import com.hieplp.url.common.handler.TokenHandler;
import com.hieplp.url.common.router.RouterHandler;
import com.hieplp.url.common.util.DiscoveryUtil;
import com.hieplp.url.gateway.service.RestService;
import io.vertx.core.http.HttpClient;
import io.vertx.ext.web.RoutingContext;
import io.vertx.servicediscovery.ServiceDiscovery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class RestServiceImpl implements RestService {

    private final ServiceDiscovery discovery;
    private final RouterHandler routerHandler;
    private final TokenHandler tokenHandler;

    @Override
    public void auth(RoutingContext context) {
        try {
            log.debug("Auth handler");

            final var token = context.request().getHeader("Authorization");
            var headers = tokenHandler.validateToken(token);

            if (!TokenType.ACCESS.getType().equals(headers.getTokenType())) {
                log.debug("Invalid token type: {}", headers.getTokenType());
                throw new UnauthorizedException("Invalid token type");
            }

            context.request().headers().set(HeaderKey.USERID.getName(), headers.getUserId());
            context.next();
        } catch (Exception e) {
            routerHandler.handleException(context, e);
        }
    }

    @Override
    public void dispatch(RoutingContext context, HttpClient httpClient, String path) {
        httpClient.request(context.request().method(), path)
                .compose(request -> {

                    request.headers().setAll(context.request().headers());

                    if (context.body().isEmpty()) {
                        return request.send();
                    }

                    return request.send(context.body().buffer());
                })
                .onSuccess(ar -> {
                    log.info("Send request to {} successfully", path);

                    ar.bodyHandler(body -> {
                        log.debug("Response body: {}", body);
                        context.response().setStatusCode(ar.statusCode());
                        context.response().headers().setAll(ar.headers());
                        context.response().end(body);
                    });
                })
                .onFailure(ar1 -> {
                    ar1.printStackTrace();
                    log.error("Failed to send request cause by {}", ar1.getMessage());
                    routerHandler.internalError(context);
                })
                .onComplete(ar1 -> {
                    ServiceDiscovery.releaseServiceObject(discovery, httpClient);
                });
    }

    @Override
    public void dispatch(RoutingContext context, DiscoveryServiceName service) {
        log.info("Dispatch request to {}", service.getName());
        DiscoveryUtil.getAllEndpoints(discovery)
                .andThen(ar -> {
                    final var path = context.request().uri();
                    log.debug("Request path: {}", path);

                    var records = ar.result();
                    var client = records.stream()
                            .filter(record -> record.getMetadata().getString(DiscoveryMetadata.NAME.getName()).equals(service.getName()))
                            .findAny();

                    if (client.isPresent()) {
                        HttpClient httpClient = discovery.getReference(client.get()).get();
                        dispatch(context, httpClient, path);
                    } else {
                        routerHandler.notFound(context, new NotFoundException("service is not found"));
                    }
                })
                .onSuccess(ar -> {
                    log.debug("Get all endpoints successfully");
                })
                .onFailure(ar -> {
                    log.error("Failed to get all endpoints cause by {}", ar.getMessage());
                    routerHandler.internalError(context);
                });
    }
}
