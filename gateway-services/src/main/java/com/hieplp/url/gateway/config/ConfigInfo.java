package com.hieplp.url.gateway.config;

import com.hieplp.url.common.config.HttpServerConfig;
import com.hieplp.url.common.config.TokenConfig;
import com.hieplp.url.common.config.ZookeeperConfig;
import lombok.Data;

@Data
public class ConfigInfo {
    private HttpServerConfig serverConfig;
    private TokenConfig tokenConfig;
    private ZookeeperConfig zookeeperConfig;
    private Integer workerPoolSize;
    private Long workerMaxExecuteTime;
}
