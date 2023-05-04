package com.hieplp.url.gateway.consumer;

import com.google.inject.Inject;
import com.hieplp.url.common.router.RouterHandler;
import com.hieplp.url.gateway.config.ConfigInfo;
import com.hieplp.url.gateway.controller.AuthController;
import com.hieplp.url.gateway.controller.UserController;
import com.hieplp.url.gateway.service.RestService;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.servicediscovery.ServiceDiscovery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class ConsumerImpl implements Consumer {

    private final Vertx vertx;
    private final ConfigInfo configInfo;
    private final ServiceDiscovery discovery;
    //
    private final RouterHandler routerHandler;
    private final RestService restService;
    //
    private final AuthController authController;
    private final UserController userController;
    //
    private Router router;

    @Override
    public Consumer init() {
        log.info("Init consumer");
        router = Router.router(vertx);
        return this;
    }

    @Override
    public Consumer cors() {
        log.info("Init cors");

        router.route().handler(CorsHandler.create()
                .addRelativeOrigin(".*")

                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.PUT)
                .allowedMethod(HttpMethod.PATCH)
                .allowedMethod(HttpMethod.DELETE)

                .allowedHeader("Content-Type")
                .allowedHeader("Authorization")
        );

        return this;
    }

    @Override
    public Consumer api() {
        log.info("Init api");

        router.route("/*").handler(BodyHandler.create());

        authController.init(router);
        userController.init(router);

//        router.route("/*")
//                .handler(BodyHandler.create())
//                .handler(context -> DiscoveryUtil.getAllEndpoints(discovery)
//                        .andThen(ar -> {
//
//                            final String path = context.request().uri();
//                            final String prefix = path.substring(0, path.indexOf("/", 1));
//                            log.debug("path: {} and prefix: {}", path, prefix);
//
//                            log.error("{}", context.request().absoluteURI());
//
//                            List<Record> records = ar.result();
//                            Optional<Record> client = records.stream()
//                                    .filter(record -> record.getMetadata().getString(DiscoveryMetadata.NAME.getName()).equals(prefix))
//                                    .findAny();
//
//                            if (client.isPresent()) {
//                                HttpClient httpClient = discovery.getReference(client.get()).get();
//                                restService.dispatch(context, httpClient, path);
//                            } else {
//                                routerHandler.notFound(context, new NotFoundException("service is not found"));
//                            }
//                        })
//                        .onSuccess(ar -> {
//                            log.debug("Get all endpoints successfully");
//                        })
//                        .onFailure(ar -> {
//                            log.error("Failed to get all endpoints cause by {}", ar.getMessage());
//                            routerHandler.internalError(context);
//                        }));

        return this;
    }

    @Override
    public Consumer start() {
        log.info("Start consumer");
        vertx
                .createHttpServer(new HttpServerOptions()
                        .setHost(configInfo.getServerConfig().getHost())
                        .setPort(configInfo.getServerConfig().getPort()))
                .requestHandler(router)
                .listen(event -> {
                    if (event.succeeded()) {
                        log.info("Listen on port {}", configInfo.getServerConfig().getPort());
                    } else {
                        log.error("Listen failed on port {} cause by {}", configInfo.getServerConfig().getPort(), event.cause().getMessage());
                    }
                });

        return this;
    }
}
