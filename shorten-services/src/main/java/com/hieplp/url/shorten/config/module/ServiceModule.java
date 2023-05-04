package com.hieplp.url.shorten.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.hieplp.url.shorten.service.UrlService;
import com.hieplp.url.shorten.service.impl.UrlServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        log.info("Config service module");
        bind(UrlService.class).to(UrlServiceImpl.class).in(Singleton.class);
    }
}
