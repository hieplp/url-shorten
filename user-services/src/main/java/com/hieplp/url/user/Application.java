package com.hieplp.url.user;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hieplp.url.common.exception.EmptyConfigException;
import com.hieplp.url.common.util.ConfigUtil;
import com.hieplp.url.user.comsumer.Consumer;
import com.hieplp.url.user.config.ConfigInfo;
import com.hieplp.url.user.config.ConfigModule;
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
        log.info("Start user service");

        if (context.config().isEmpty()) {
            log.error("Config file is empty");
            throw new EmptyConfigException("Config file is empty");
        }

        // Load config
        ConfigInfo configInfo = ConfigUtil.loadConfig(context.config().encode(), ConfigInfo.class);

        // Zookeeper cluster manager
        JsonObject zkConfig = new JsonObject();
        zkConfig.put("zookeeperHosts", configInfo.getZookeeperConfig().getHost());
        zkConfig.put("rootPath", configInfo.getZookeeperConfig().getRootPath());
        ClusterManager mgr = new ZookeeperClusterManager(zkConfig);

        // Vertx cluster
        VertxOptions options = new VertxOptions()
                .setWorkerPoolSize(configInfo.getWorkerPoolSize())
                .setMaxWorkerExecuteTime(configInfo.getWorkerMaxExecuteTime())
                .setClusterManager(mgr);
        Vertx.clusteredVertx(options)
                .onSuccess(res -> {
                    this.vertx = res;

                    injector = Guice.createInjector(new ConfigModule(vertx, configInfo));
                    consumer = injector.getInstance(Consumer.class);
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

    @Override
    public void stop() throws Exception {
        log.info("Stop user service");
        consumer.stop();
    }
}
