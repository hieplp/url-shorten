package com.hieplp.url.common.handler;

import io.vertx.core.buffer.Buffer;
import io.vertx.kafka.client.consumer.KafkaConsumerRecord;

public interface KafkaConsumerHandler {
    void consume(KafkaConsumerRecord<String, Buffer> record, Handler handler);

    @FunctionalInterface
    interface Handler {
        void handle(Buffer buffer);
    }
}
