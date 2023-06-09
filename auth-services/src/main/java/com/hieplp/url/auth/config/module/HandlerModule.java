package com.hieplp.url.auth.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.hieplp.url.common.handler.AuthHandler;
import com.hieplp.url.common.handler.TokenHandler;
import com.hieplp.url.common.handler.impl.AuthHandlerImpl;
import com.hieplp.url.common.handler.impl.TokenHandlerImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HandlerModule extends AbstractModule {
    @Override
    protected void configure() {
        log.info("Configuring handler module");
        bind(AuthHandler.class).to(AuthHandlerImpl.class).in(Singleton.class);
        bind(TokenHandler.class).to(TokenHandlerImpl.class).in(Singleton.class);
    }
}
