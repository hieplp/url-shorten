package com.hieplp.url.common.config;

import lombok.Data;

@Data
public class KafkaProducerConfig {
    private String bootstrapServers;
    private String keySerializer;
    private String valueSerializer;
    private String groupId;
}
