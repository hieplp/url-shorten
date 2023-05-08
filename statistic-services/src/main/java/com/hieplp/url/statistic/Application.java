package com.hieplp.url.statistic;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hieplp.url.common.exception.EmptyConfigException;
import com.hieplp.url.common.util.ConfigUtil;
import com.hieplp.url.statistic.config.ConfigInfo;
import com.hieplp.url.statistic.config.ConfigModule;
import com.hieplp.url.statistic.consumer.Consumer;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.zookeeper.ZookeeperClusterManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application extends AbstractVerticle {

    public static Injector injector;
    public static Consumer consumer;

    @Override
    public void start() {
        log.info("Start statistic service");

        if (context.config().isEmpty()) {
            log.error("Config file is empty");
            throw new EmptyConfigException("Config file is empty");
        }

        // Load config
        ConfigInfo configInfo = ConfigUtil.loadConfig(context.config().encode(), ConfigInfo.class);

        // Zookeeper cluster manager
        JsonObject zkConfig = new JsonObject();
        ClusterManager mgr = new ZookeeperClusterManager(zkConfig);

        // Vertx cluster
        VertxOptions options = new VertxOptions()
                .setWorkerPoolSize(configInfo.getWorkerPoolSize())
                .setMaxWorkerExecuteTime(configInfo.getWorkerMaxExecuteTime())
                .setClusterManager(mgr);
        Vertx.clusteredVertx(options)
                .onSuccess(res -> {
                    this.vertx = res;

                    injector = Guice.createInjector(new ConfigModule(this.vertx, configInfo));
                    consumer = injector.getInstance(Consumer.class);
                    consumer
                            .init()
                            .api()
                            .kafka()
                            .start();
                })
                .onFailure(err -> {
                    log.error("Failed to start vertx cluster", err);
                });
    }

    @Override
    public void stop() throws Exception {
        log.info("Stop statistic service");
        consumer.stop();
    }
}