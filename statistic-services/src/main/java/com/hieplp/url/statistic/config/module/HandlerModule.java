package com.hieplp.url.statistic.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.hieplp.url.common.handler.AuthHandler;
import com.hieplp.url.common.handler.impl.AuthHandlerImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HandlerModule extends AbstractModule {
    @Override
    protected void configure() {
        log.info("Configuring handler module");
        bind(AuthHandler.class).to(AuthHandlerImpl.class).in(Singleton.class);
    }
}
