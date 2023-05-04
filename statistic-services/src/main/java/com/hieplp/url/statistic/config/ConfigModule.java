package com.hieplp.url.statistic.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.hieplp.url.common.util.ConfigUtil;
import com.hieplp.url.statistic.config.module.*;
import com.hieplp.url.statistic.consumer.Consumer;
import com.hieplp.url.statistic.consumer.ConsumerImpl;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigModule extends AbstractModule {
    private final ConfigInfo configInfo;
    private final Vertx vertx;

    public ConfigModule(Context context) {
        this.configInfo = ConfigUtil.loadConfig(context.config().encode(), ConfigInfo.class);
        this.vertx = Vertx.vertx(new VertxOptions()
                .setWorkerPoolSize(configInfo.getWorkerPoolSize())
                .setMaxWorkerExecuteTime(configInfo.getWorkerMaxExecuteTime())
        );
    }

    @Provides
    @Singleton
    public Vertx getVertx() {
        return vertx;
    }

    @Provides
    @Singleton
    public ConfigInfo getConfigInfo() {
        return configInfo;
    }

    @Override
    protected void configure() {
        log.info("Config module");
        bind(Consumer.class).to(ConsumerImpl.class).in(Singleton.class);
        install(new ServiceModule());
        install(new RouterModule());
        install(new SqlModule(getConfigInfo()));
        install(new RsaModule(getConfigInfo()));
        install(new HandlerModule());
        install(new KafkaModule(getVertx(), getConfigInfo().getKafkaConfig()));
    }
}
