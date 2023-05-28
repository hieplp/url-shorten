package com.hieplp.url.user.config;

import com.hieplp.url.common.config.DiscoveryConfig;
import com.hieplp.url.common.config.HttpServerConfig;
import com.hieplp.url.common.config.SqlConfig;
import com.hieplp.url.common.config.ZookeeperConfig;
import lombok.Getter;

@Getter
public class ConfigInfo {
    private HttpServerConfig serverConfig;
    private DiscoveryConfig discoveryConfig;
    private SqlConfig sqlConfig;
    private ZookeeperConfig zookeeperConfig;
    private Integer workerPoolSize;
    private Long workerMaxExecuteTime;
}
