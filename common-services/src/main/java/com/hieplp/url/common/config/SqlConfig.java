package com.hieplp.url.common.config;

import lombok.Getter;

@Getter
public class SqlConfig {
    private String driverClassName;
    private String jdbcUrl;
    private Integer maxPoolSize;
    private Integer connectionTimeout;
    private String serverTimezone;
    private String useLegacyDatetimeCode;
    private boolean autoCommit;
    private String username;
    private String password;
}
