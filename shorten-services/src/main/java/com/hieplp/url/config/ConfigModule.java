package com.hieplp.url.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.hieplp.url.common.model.UrlModel;
import com.hieplp.url.common.util.ConfigUtil;
import com.hieplp.url.comsumer.Consumer;
import com.hieplp.url.comsumer.ConsumerImpl;
import com.hieplp.url.config.module.RouterModule;
import com.hieplp.url.config.module.ServiceModule;
import com.hieplp.url.config.module.SqlModule;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

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

//    @Provides
//    @Singleton
//    public Cache<String, UrlModel> getUrlCache() {
//        return CacheBuilder.newBuilder()
//                .maximumSize(getConfigInfo().getCacheConfig().getMaximumSize())
//                .expireAfterWrite(getConfigInfo().getCacheConfig().getExpireAfterWrite(), TimeUnit.MINUTES)
//                .
//                .build()
//                ;
//    }

    @Override
    protected void configure() {
        log.info("Config module");
        bind(Consumer.class).to(ConsumerImpl.class).in(Singleton.class);
        install(new ServiceModule());
        install(new RouterModule());
        install(new SqlModule(getConfigInfo()));
    }
}
