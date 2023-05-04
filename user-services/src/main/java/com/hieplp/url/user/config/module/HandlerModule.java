package com.hieplp.url.user.config.module;

import com.google.inject.AbstractModule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HandlerModule extends AbstractModule {
    @Override
    protected void configure() {
        log.info("Configuring handler module");
    }
}
