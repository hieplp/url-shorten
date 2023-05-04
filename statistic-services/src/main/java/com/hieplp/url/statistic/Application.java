package com.hieplp.url.statistic;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hieplp.url.common.exception.EmptyConfigException;
import com.hieplp.url.statistic.config.ConfigModule;
import com.hieplp.url.statistic.consumer.Consumer;
import io.vertx.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application extends AbstractVerticle {

    public static Injector injector;

    @Override
    public void start() {
        log.info("Start statistic service");

        if (context.config().isEmpty()) {
            log.error("Config file is empty");
            throw new EmptyConfigException("Config file is empty");
        }

        injector = Guice.createInjector(new ConfigModule(context));
        Consumer consumer = injector.getInstance(Consumer.class);
        consumer
                .init()
                .kafka()
                .cors()
                .api()
                .start();
    }
}
