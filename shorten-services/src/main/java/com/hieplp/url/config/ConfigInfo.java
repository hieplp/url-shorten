package com.hieplp.url.config;

import com.hieplp.url.common.config.CacheConfig;
import com.hieplp.url.common.config.HttpServerConfig;
import com.hieplp.url.common.config.SqlConfig;
import lombok.Getter;

@Getter
public class ConfigInfo {
    private HttpServerConfig serverConfig;
    private SqlConfig sqlConfig;
    private CacheConfig cacheConfig;
    private Integer shortUrlLength;
    private Integer workerPoolSize;
    private Long workerMaxExecuteTime;
}
