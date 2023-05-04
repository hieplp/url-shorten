package com.hieplp.url.auth.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.hieplp.url.auth.service.AuthService;
import com.hieplp.url.auth.service.impl.AuthServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        log.info("Config service module");
        bind(AuthService.class).to(AuthServiceImpl.class).in(Singleton.class);
    }
}
