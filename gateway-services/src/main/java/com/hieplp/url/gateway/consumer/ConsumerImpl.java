package com.hieplp.url.gateway.consumer;

import com.google.inject.Inject;
import com.hieplp.url.common.util.CorsUtil;
import com.hieplp.url.gateway.config.ConfigInfo;
import com.hieplp.url.gateway.controller.AuthController;
import com.hieplp.url.gateway.controller.UrlController;
import com.hieplp.url.gateway.controller.UserController;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
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
        CorsUtil.cors(router);
        return this;
    }

    @Override
    public Consumer api() {
        log.info("Init api");

        router.route("/*").handler(BodyHandler.create());

        authController.init(router);
        urlController.init(router);
        userController.init(router); // Should be the last one because it matches all routes with /user/*

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
