package com.hieplp.url.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.hieplp.url.handler.AuthHandler;
import com.hieplp.url.handler.UrlHandler;
import com.hieplp.url.handler.impl.AuthHandlerImpl;
import com.hieplp.url.handler.impl.UrlHandlerImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HandlerModule extends AbstractModule {
    @Override
    protected void configure() {
        log.info("Configuring handler module");
        bind(AuthHandler.class).to(AuthHandlerImpl.class).in(Singleton.class);
        bind(UrlHandler.class).to(UrlHandlerImpl.class).in(Singleton.class);
    }
}
