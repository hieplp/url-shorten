package com.hieplp.url.statistic.consumer;


import com.google.inject.Inject;
import com.hieplp.url.common.constants.statistic.StatisticTopic;
import com.hieplp.url.common.handler.KafkaConsumerHandler;
import com.hieplp.url.common.util.DiscoveryUtil;
import com.hieplp.url.statistic.config.ConfigInfo;
import com.hieplp.url.statistic.factory.StatisticFactory;
import com.hieplp.url.statistic.stragety.StatisticStrategy;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.kafka.client.consumer.KafkaConsumer;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class ConsumerImpl implements Consumer {

    private final Vertx vertx;
    private final ConfigInfo configInfo;
    private final ServiceDiscovery serviceDiscovery;
    private final Record discoveryRecord;
    //
    private final KafkaConsumer<String, Buffer> kafkaConsumer;
    private final KafkaConsumerHandler kafkaConsumerHandler;
    //
    private final StatisticFactory statisticFactory;
    //
    private Router router;


    @Override
    public Consumer init() {
        log.info("Init consumer");
        router = Router.router(vertx);
        return this;
    }

    @Override
    public Consumer kafka() {
        log.info("Init kafka");

        Set<String> topics = new HashSet<>();
        Arrays.stream(StatisticTopic.values())
                .forEach(statisticTopic -> topics.add(statisticTopic.getName()));

        kafkaConsumer.subscribe(topics)
                .onSuccess(success -> log.info("Subscribe topics {} success", topics))
                .onFailure(failure -> log.error("Subscribe topics {} failure: {}", topics, failure));


        kafkaConsumer.handler(record -> {
            StatisticTopic topic = StatisticTopic.safeValueOf(record.topic());
            StatisticStrategy strategy = statisticFactory.getStrategy(topic);
            strategy
                    .validate(record.value())
                    .saveHistory()
                    .executeStatistic();
        });

        return this;
    }

    @Override
    public Consumer api() {
        log.info("Init api");


        return this;
    }

    @Override
    public Consumer start() {
        log.info("Start consumer");
        vertx
                .createHttpServer(new HttpServerOptions()
                        .setHost(configInfo.getServerConfig().getHost())
                        .setPort(configInfo.getServerConfig().getPort()))
                .requestHandler(router)
                .listen(event -> {
                    if (event.succeeded()) {
                        log.info("Listen on port {}", configInfo.getServerConfig().getPort());
                    } else {
                        log.error("Listen failed on port {} cause by {}", configInfo.getServerConfig().getPort(), event.cause().getMessage());
                    }
                });
        return this;
    }

    @Override
    public Consumer stop() {
        log.info("Stop consumer");
        DiscoveryUtil.unPublicService(serviceDiscovery, discoveryRecord);
        return this;
    }
}