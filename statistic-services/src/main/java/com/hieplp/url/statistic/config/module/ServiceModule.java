package com.hieplp.url.statistic.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.hieplp.url.statistic.service.StatisticService;
import com.hieplp.url.statistic.service.impl.StatisticServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        log.info("Config service module");
        bind(StatisticService.class).to(StatisticServiceImpl.class).in(Scopes.SINGLETON);
    }
}
