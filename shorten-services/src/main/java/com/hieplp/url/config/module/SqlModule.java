package com.hieplp.url.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.hieplp.url.common.config.SqlConfig;
import com.hieplp.url.common.util.States;
import com.hieplp.url.config.ConfigInfo;
import com.hieplp.url.repository.source.UrlRepository;
import com.hieplp.url.repository.source.impl.UrlRepositoryImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class SqlModule extends AbstractModule {

    private final ConfigInfo configInfo;

    private final int DEFAULT_IDLE_TIMEOUT = 600000;
    private final int DEFAULT_CONNECTION_TIMEOUT = 10000;
    private final int DEFAULT_MAX_POOL_SIZE = 8;
    private final int DEFAULT_MIN_IDLE = 4;

    @Override
    protected void configure() {
        log.info("Configuring SQL module");
        bind(UrlRepository.class).to(UrlRepositoryImpl.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    private HikariDataSource getDataSource() {
        log.info("Configuring SQL data source");

        SqlConfig config = configInfo.getSqlConfig();

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(config.getDriverClassName());
        hikariConfig.setJdbcUrl(config.getJdbcUrl());
        hikariConfig.setUsername(config.getUsername());
        hikariConfig.setPassword(config.getPassword());
        hikariConfig.setAutoCommit(true);
        hikariConfig.setConnectionTimeout(DEFAULT_CONNECTION_TIMEOUT);
        hikariConfig.setIdleTimeout(DEFAULT_IDLE_TIMEOUT);
        hikariConfig.setMinimumIdle(DEFAULT_MIN_IDLE);
        hikariConfig.setMaximumPoolSize(States.isNotNull(config.getMaxPoolSize()) ? config.getMaxPoolSize() : DEFAULT_MAX_POOL_SIZE);

        hikariConfig.addDataSourceProperty("serverTimezone", config.getServerTimezone());
        hikariConfig.addDataSourceProperty("useLegacyDatetimeCode", config.getUseLegacyDatetimeCode());

        return new HikariDataSource(hikariConfig);
    }
}
