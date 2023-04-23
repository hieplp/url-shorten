package com.hieplp.url.config;

import com.hieplp.url.common.config.*;
import lombok.Getter;

@Getter
public class ConfigInfo {
    private HttpServerConfig serverConfig;
    private SqlConfig sqlConfig;
    private CacheConfig cacheConfig;
    private TokenConfig tokenConfig;
    private PasswordConfig passwordConfig;
    private Integer aliasLength;
    private String urlHost;
    private Integer workerPoolSize;
    private Long workerMaxExecuteTime;
}
