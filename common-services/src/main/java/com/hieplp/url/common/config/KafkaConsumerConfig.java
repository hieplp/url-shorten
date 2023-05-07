package com.hieplp.url.common.config;

import lombok.Data;

@Data
public class KafkaConsumerConfig {
    private String bootstrapServers;
    private String keyDeserializer;
    private String valueDeserializer;
    private String offsetReset;
    private String groupId;
    private String autoCommit;
}
