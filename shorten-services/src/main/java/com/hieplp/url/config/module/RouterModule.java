package com.hieplp.url.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.hieplp.url.common.router.RouterHandler;
import com.hieplp.url.common.router.impl.RouterHandlerImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RouterModule extends AbstractModule {
    @Override
    protected void configure() {
        log.info("Config router module");
        bind(RouterHandler.class).to(RouterHandlerImpl.class).in(Singleton.class);
    }
}
