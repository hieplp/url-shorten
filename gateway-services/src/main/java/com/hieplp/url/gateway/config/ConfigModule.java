package com.hieplp.url.gateway.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.hieplp.url.gateway.config.module.HandlerModule;
import com.hieplp.url.gateway.config.module.RouterModule;
import com.hieplp.url.gateway.config.module.ServiceModule;
import com.hieplp.url.gateway.consumer.Consumer;
import com.hieplp.url.gateway.consumer.ConsumerImpl;
import io.vertx.core.Vertx;
import io.vertx.servicediscovery.ServiceDiscovery;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigModule extends AbstractModule {

    private final Vertx vertx;
    private final ConfigInfo configInfo;
    private final ServiceDiscovery discovery;

    public ConfigModule(Vertx vertx,
                        ConfigInfo configInfo) {
        this.vertx = vertx;
        this.configInfo = configInfo;
        this.discovery = ServiceDiscovery.create(vertx);
    }

    @Provides
    @Singleton
    public ConfigInfo getConfigInfo() {
        return configInfo;
    }

    @Provides
    @Singleton
    public Vertx getVertx() {
        return vertx;
    }

    @Provides
    @Singleton
    public ServiceDiscovery getDiscovery() {
        return discovery;
    }


    @Override
    protected void configure() {
        log.info("Config module");
        bind(Consumer.class).to(ConsumerImpl.class).in(Singleton.class);
        install(new ServiceModule());
        install(new RouterModule());
        install(new HandlerModule());
    }
}
