package com.hieplp.url.shorten.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.hieplp.url.common.constants.ApiConfig;
import com.hieplp.url.common.constants.discovery.DiscoveryServiceName;
import com.hieplp.url.common.util.DiscoveryUtil;
import com.hieplp.url.shorten.comsumer.Consumer;
import com.hieplp.url.shorten.comsumer.ConsumerImpl;
import com.hieplp.url.shorten.config.module.*;
import io.vertx.core.Vertx;
import io.vertx.servicediscovery.ServiceDiscovery;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigModule extends AbstractModule {
    private final ConfigInfo configInfo;
    private final Vertx vertx;
    private final ServiceDiscovery discovery;

    public ConfigModule(Vertx vertx,
                        ConfigInfo configInfo) {
        this.vertx = vertx;
        this.configInfo = configInfo;
        //
        this.discovery = ServiceDiscovery.create(vertx);
        DiscoveryUtil.publicService(this.discovery,
                DiscoveryServiceName.AUTH,
                configInfo.getServerConfig().getHost(),
                configInfo.getServerConfig().getPort(),
                ApiConfig.Auth.PREFIX);
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
        install(new SqlModule(getConfigInfo()));
        install(new RsaModule(getConfigInfo()));
        install(new HandlerModule());
    }
}
