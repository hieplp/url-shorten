package com.hieplp.url.common.handler;

public interface KafkaProducerHandler {
    void send(String topic, String key, Object value);
}
