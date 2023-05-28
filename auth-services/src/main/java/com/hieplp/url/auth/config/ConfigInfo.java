package com.hieplp.url.auth.config;

import com.hieplp.url.common.config.*;
import lombok.Getter;

@Getter
public class ConfigInfo {
    private HttpServerConfig serverConfig;
    private DiscoveryConfig discoveryConfig;
    private SqlConfig sqlConfig;
    private TokenConfig tokenConfig;
    private PasswordConfig passwordConfig;
    private ZookeeperConfig zookeeperConfig;
    private Integer workerPoolSize;
    private Long workerMaxExecuteTime;
}
