package com.hieplp.url.auth.config;

import com.hieplp.url.common.config.HttpServerConfig;
import com.hieplp.url.common.config.PasswordConfig;
import com.hieplp.url.common.config.SqlConfig;
import com.hieplp.url.common.config.TokenConfig;
import lombok.Getter;

@Getter
public class ConfigInfo {
    private HttpServerConfig serverConfig;
    private SqlConfig sqlConfig;
    private TokenConfig tokenConfig;
    private PasswordConfig passwordConfig;
    private Integer workerPoolSize;
    private Long workerMaxExecuteTime;
}
