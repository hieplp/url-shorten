package com.hieplp.url.statistic.config;

import com.hieplp.url.common.config.*;
import lombok.Getter;

@Getter
public class ConfigInfo {
    private HttpServerConfig serverConfig;
    private DiscoveryConfig discoveryConfig;
    private SqlConfig sqlConfig;
    private KafkaConsumerConfig kafkaConfig;
    private ElasticConfig elasticConfig;
    private ZookeeperConfig zookeeperConfig;
    private Integer workerPoolSize;
    private Long workerMaxExecuteTime;
}
