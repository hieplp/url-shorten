package com.hieplp.url.shorten.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.hieplp.url.common.config.KafkaProducerConfig;
import com.hieplp.url.common.handler.KafkaProducerHandler;
import com.hieplp.url.common.handler.impl.KafkaProducerHandlerImpl;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.kafka.client.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class KafkaModule extends AbstractModule {

    private final Vertx vertx;
    private final KafkaProducerConfig kafkaConfig;

    @Provides
    @Singleton
    public KafkaProducer<String, Buffer> getKafkaProducer() {
        return KafkaProducer.create(vertx, getKafkaConfigMap());
    }

    @Override
    protected void configure() {
        log.info("Configuring kafka module");
        bind(KafkaProducerHandler.class).to(KafkaProducerHandlerImpl.class).in(Scopes.SINGLETON);
    }

    private Map<String, String> getKafkaConfigMap() {
        Map<String, String> config = new HashMap<>();
        config.put("bootstrap.servers", kafkaConfig.getBootstrapServers());
        config.put("key.serializer", kafkaConfig.getKeySerializer());
        config.put("value.serializer", kafkaConfig.getValueSerializer());
        config.put("acks", "1");
        return config;
    }
}
