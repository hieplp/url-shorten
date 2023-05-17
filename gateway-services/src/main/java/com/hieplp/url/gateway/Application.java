package com.hieplp.url.gateway;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hieplp.url.common.exception.EmptyConfigException;
import com.hieplp.url.common.util.ConfigUtil;
import com.hieplp.url.gateway.config.ConfigInfo;
import com.hieplp.url.gateway.config.ConfigModule;
import com.hieplp.url.gateway.consumer.Consumer;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.spi.cluster.zookeeper.ZookeeperClusterManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application extends AbstractVerticle {

    public static Injector injector;

    @Override
    public void start() {
        log.info("Start gateway service");

        if (context.config().isEmpty()) {
            log.error("Config file is empty");
            throw new EmptyConfigException("Config file is empty");
        }

        // Load config
        var configInfo = ConfigUtil.loadConfig(context.config().encode(), ConfigInfo.class);

        // Zookeeper cluster manager
        var zkConfig = new JsonObject();
        var mgr = new ZookeeperClusterManager(zkConfig);

        // Vertx cluster
        var options = new VertxOptions()
                .setWorkerPoolSize(configInfo.getWorkerPoolSize())
                .setMaxWorkerExecuteTime(configInfo.getWorkerMaxExecuteTime())
                .setClusterManager(mgr);
        Vertx.clusteredVertx(options)
                .onSuccess(res -> {
                    this.vertx = res;

                    injector = Guice.createInjector(new ConfigModule(this.vertx, configInfo));
                    var consumer = injector.getInstance(Consumer.class);
                    consumer
                            .init()
                            .cors()
                            .api()
                            .start();
                })
                .onFailure(err -> {
                    log.error("Failed to start vertx cluster", err);
                });
    }
}
