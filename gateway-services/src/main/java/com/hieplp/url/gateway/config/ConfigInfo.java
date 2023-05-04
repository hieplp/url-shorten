package com.hieplp.url.gateway.config;

import com.hieplp.url.common.config.DiscoveryConfig;
import com.hieplp.url.common.config.HttpServerConfig;
import lombok.Data;

@Data
public class ConfigInfo {
    private HttpServerConfig serverConfig;
    private DiscoveryConfig discoveryConfig;
    private Integer workerPoolSize;
    private Long workerMaxExecuteTime;
}
