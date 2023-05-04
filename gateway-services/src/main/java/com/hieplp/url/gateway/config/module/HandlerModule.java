package com.hieplp.url.gateway.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.hieplp.url.common.handler.TokenHandler;
import com.hieplp.url.common.handler.impl.TokenHandlerImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HandlerModule extends AbstractModule {
    @Override
    protected void configure() {
        log.info("Configuring handler module");
        bind(TokenHandler.class).to(TokenHandlerImpl.class).in(Scopes.SINGLETON);
    }
}
