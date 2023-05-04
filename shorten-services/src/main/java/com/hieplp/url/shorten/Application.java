package com.hieplp.url.shorten;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hieplp.url.common.exception.EmptyConfigException;
import com.hieplp.url.shorten.comsumer.Consumer;
import com.hieplp.url.shorten.config.ConfigModule;
import io.vertx.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application extends AbstractVerticle {

    public static Injector injector;

    @Override
    public void start() {
        log.info("Start url-shortener service");

        if (context.config().isEmpty()) {
            log.error("Config file is empty");
            throw new EmptyConfigException("Config file is empty");
        }

        injector = Guice.createInjector(new ConfigModule(context));
        Consumer consumer = injector.getInstance(Consumer.class);
        consumer
                .init()
                .cors()
                .api()
                .start();
    }
}
