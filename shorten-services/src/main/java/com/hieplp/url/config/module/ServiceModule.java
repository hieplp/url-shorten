package com.hieplp.url.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.hieplp.url.service.UrlService;
import com.hieplp.url.service.impl.UrlServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        log.info("Config service module");
        bind(UrlService.class).to(UrlServiceImpl.class).in(Singleton.class);
    }
}
