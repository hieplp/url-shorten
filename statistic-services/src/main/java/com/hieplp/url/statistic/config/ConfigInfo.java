package com.hieplp.url.statistic.config;

import com.hieplp.url.common.config.*;
import lombok.Getter;

@Getter
public class ConfigInfo {
    private HttpServerConfig serverConfig;
    private SqlConfig sqlConfig;
    private CacheConfig cacheConfig;
    private TokenConfig tokenConfig;
    private KafkaConsumerConfig kafkaConfig;
    private Integer workerPoolSize;
    private Long workerMaxExecuteTime;
}
