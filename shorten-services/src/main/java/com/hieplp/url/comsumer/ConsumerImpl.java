package com.hieplp.url.comsumer;


import com.google.inject.Inject;
import com.hieplp.url.config.ConfigInfo;
import com.hieplp.url.controller.AuthController;
import com.hieplp.url.controller.UrlController;
import com.hieplp.url.controller.UserController;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class ConsumerImpl implements Consumer {

    private final Vertx vertx;
    private final ConfigInfo configInfo;
    //
    private final AuthController authController;
    private final UserController userController;
    private final UrlController urlController;

    private Router router;


    @Override
    public Consumer init() {
        log.info("Init consumer");
        router = Router.router(vertx);
        return this;
    }

    @Override
    public Consumer api() {
        log.info("Init api");

        authController.init(router);
        userController.init(router);
        urlController.init(router);

        return this;
    }

    @Override
    public Consumer cors() {
        log.info("Init cors");

        router.route().handler(CorsHandler.create("*")

                .allowedMethod(io.vertx.core.http.HttpMethod.GET)
                .allowedMethod(io.vertx.core.http.HttpMethod.POST)
                .allowedMethod(io.vertx.core.http.HttpMethod.PUT)
                .allowedMethod(io.vertx.core.http.HttpMethod.DELETE)

                .allowedHeader("Content-Type")
                .allowedHeader("Authorization")
        );

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
