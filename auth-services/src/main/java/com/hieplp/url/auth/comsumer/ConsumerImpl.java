package com.hieplp.url.auth.comsumer;


import com.google.inject.Inject;
import com.hieplp.url.auth.config.ConfigInfo;
import com.hieplp.url.auth.controller.AuthController;
import com.hieplp.url.common.util.CorsUtil;
import com.hieplp.url.common.util.DiscoveryUtil;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class ConsumerImpl implements Consumer {

    private final Vertx vertx;
    private final ConfigInfo configInfo;
    private final ServiceDiscovery serviceDiscovery;
    private final Record discoveryRecord;
    //
    private final AuthController authController;
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

        authController.init(router);

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

    @Override
    public Consumer stop() {
        log.info("Stop consumer");
        DiscoveryUtil.unPublicService(serviceDiscovery, discoveryRecord);
        return this;
    }
}
