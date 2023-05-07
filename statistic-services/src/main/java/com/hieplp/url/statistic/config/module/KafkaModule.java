package com.hieplp.url.statistic.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.hieplp.url.common.config.KafkaConsumerConfig;
import com.hieplp.url.common.handler.KafkaConsumerHandler;
import com.hieplp.url.common.handler.impl.KafkaConsumerHandlerImpl;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.kafka.client.consumer.KafkaConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class KafkaModule extends AbstractModule {

    private final Vertx vertx;
    private final KafkaConsumerConfig kafkaConfig;

    @Provides
    @Singleton
    public KafkaConsumer<String, Buffer> getKafkaConsumer() {
        return KafkaConsumer.create(vertx, getKafkaConfigMap());
    }

    @Override
    protected void configure() {
        log.info("Configuring kafka module");
        bind(KafkaConsumerHandler.class).to(KafkaConsumerHandlerImpl.class).in(Scopes.SINGLETON);
    }

    private Map<String, String> getKafkaConfigMap() {
        Map<String, String> config = new HashMap<>();
        config.put("bootstrap.servers", kafkaConfig.getBootstrapServers());
        config.put("key.deserializer", kafkaConfig.getKeyDeserializer());
        config.put("value.deserializer", kafkaConfig.getValueDeserializer());
        config.put("group.id", kafkaConfig.getGroupId());
        config.put("auto.offset.reset", kafkaConfig.getOffsetReset());
        config.put("enable.auto.commit", kafkaConfig.getAutoCommit());
        return config;
    }
}
