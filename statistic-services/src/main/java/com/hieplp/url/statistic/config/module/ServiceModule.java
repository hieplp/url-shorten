package com.hieplp.url.statistic.config.module;

import com.google.inject.AbstractModule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        log.info("Config service module");
    }
}
