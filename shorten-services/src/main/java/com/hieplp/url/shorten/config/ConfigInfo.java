package com.hieplp.url.shorten.config;

import com.hieplp.url.common.config.*;
import lombok.Getter;

@Getter
public class ConfigInfo {
    private HttpServerConfig serverConfig;
    private DiscoveryConfig discoveryConfig;
    private SqlConfig sqlConfig;
    private CacheConfig cacheConfig;
    private KafkaProducerConfig kafkaConfig;
    private ZookeeperConfig zookeeperConfig;
    private Integer aliasLength;
    private String urlHost;
    private Integer workerPoolSize;
    private Long workerMaxExecuteTime;
}
