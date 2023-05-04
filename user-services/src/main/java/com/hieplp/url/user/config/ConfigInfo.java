package com.hieplp.url.user.config;

import com.hieplp.url.common.config.HttpServerConfig;
import com.hieplp.url.common.config.SqlConfig;
import lombok.Getter;

@Getter
public class ConfigInfo {
    private HttpServerConfig serverConfig;
    private SqlConfig sqlConfig;
    private Integer workerPoolSize;
    private Long workerMaxExecuteTime;
}
