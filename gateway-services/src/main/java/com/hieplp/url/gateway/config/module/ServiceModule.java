package com.hieplp.url.gateway.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.hieplp.url.gateway.service.RestService;
import com.hieplp.url.gateway.service.impl.RestServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        log.info("Config service module");
        bind(RestService.class).to(RestServiceImpl.class).in(Scopes.SINGLETON);
    }
}
