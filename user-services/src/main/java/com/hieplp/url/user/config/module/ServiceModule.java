package com.hieplp.url.user.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.hieplp.url.user.service.UserService;
import com.hieplp.url.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        log.info("Config service module");
        bind(UserService.class).to(UserServiceImpl.class).in(Singleton.class);
    }
}
