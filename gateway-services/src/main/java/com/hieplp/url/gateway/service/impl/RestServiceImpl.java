package com.hieplp.url.gateway.service.impl;

import com.google.inject.Inject;
import com.hieplp.url.common.router.RouterHandler;
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

    @Override
    public void dispatch(RoutingContext context, HttpClient httpClient, String path) {
        httpClient.request(context.request().method(), path)
                .compose(request -> {

                    // TODO: Check token here

                    request.headers().setAll(context.request().headers());

                    if (context.body().isEmpty()) {
                        return request.send();
                    }

                    return request.send(context.body().buffer());
                })
                .onSuccess(ar1 -> {
                    log.info("Send request to {} successfully", path);

                    ar1.bodyHandler(body -> {
                        log.debug("Response body: {}", body);
                        context.response().setStatusCode(ar1.statusCode());
                        context.response().headers().setAll(ar1.headers());
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
}
