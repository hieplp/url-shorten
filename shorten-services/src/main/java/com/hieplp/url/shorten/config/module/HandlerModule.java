package com.hieplp.url.shorten.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.hieplp.url.shorten.handler.UrlHandler;
import com.hieplp.url.shorten.handler.impl.UrlHandlerImpl;
import com.hieplp.url.common.handler.AuthHandler;
import com.hieplp.url.common.handler.impl.AuthHandlerImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HandlerModule extends AbstractModule {
    @Override
    protected void configure() {
        log.info("Configuring handler module");
        bind(UrlHandler.class).to(UrlHandlerImpl.class).in(Singleton.class);
    }
}
