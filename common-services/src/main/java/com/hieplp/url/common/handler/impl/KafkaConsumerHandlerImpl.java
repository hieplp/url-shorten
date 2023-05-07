package com.hieplp.url.common.handler.impl;

import com.google.inject.Inject;
import com.hieplp.url.common.handler.KafkaConsumerHandler;
import io.vertx.core.buffer.Buffer;
import io.vertx.kafka.client.consumer.KafkaConsumer;
import io.vertx.kafka.client.consumer.KafkaConsumerRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class KafkaConsumerHandlerImpl implements KafkaConsumerHandler {

    private final KafkaConsumer<String, Buffer> consumer;

    @Override
    public void consume(KafkaConsumerRecord<String, Buffer> record, Handler handler) {
        log.info("Consume record from kafka topic: {}, key: {} and value: {}", record.topic(), record.key(), record.value());
        handler.handle(record.value());
    }
}
