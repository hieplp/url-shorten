package com.hieplp.url.shorten.config;

import com.hieplp.url.common.config.*;
import lombok.Getter;

@Getter
public class ConfigInfo {
    private HttpServerConfig serverConfig;
    private SqlConfig sqlConfig;
    private CacheConfig cacheConfig;
    private Integer aliasLength;
    private String urlHost;
    private Integer workerPoolSize;
    private Long workerMaxExecuteTime;
}
