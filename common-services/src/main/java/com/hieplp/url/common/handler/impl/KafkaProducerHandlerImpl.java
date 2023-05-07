package com.hieplp.url.common.handler.impl;

import com.google.inject.Inject;
import com.hieplp.url.common.handler.KafkaProducerHandler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class KafkaProducerHandlerImpl implements KafkaProducerHandler {

    private final KafkaProducer<String, Buffer> producer;

    @Override
    public void send(String topic, String key, Object value) {
        log.info("Send record to kafka topic: {}, key: {}, value: {}", topic, key, value);
        producer.send(KafkaProducerRecord.create(topic, key, Json.encodeToBuffer(value)))
                .onSuccess(success -> log.info("Send record to kafka topic: {}, key: {}, value: {} success", topic, key, value))
                .onFailure(failure -> log.error("Send record to kafka topic: {}, key: {}, value: {} failure: {}", topic, key, value, failure));
    }
}
