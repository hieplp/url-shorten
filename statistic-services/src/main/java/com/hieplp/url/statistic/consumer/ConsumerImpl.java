package com.hieplp.url.statistic.consumer;


import com.google.inject.Inject;
import com.hieplp.url.common.constants.statistic.StatisticTopic;
import com.hieplp.url.statistic.config.ConfigInfo;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.kafka.client.consumer.KafkaConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class ConsumerImpl implements Consumer {

    private final Vertx vertx;
    private final ConfigInfo configInfo;
    //
    private final KafkaConsumer<String, String> kafkaConsumer;

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

        Arrays.stream(StatisticTopic.values())
                .forEach(statisticTopic -> kafkaConsumer.subscribe(statisticTopic.getName())
                        .onSuccess(success -> log.info("Subscribe topic {} success", statisticTopic.getName()))
                        .onFailure(failure -> log.error("Subscribe topic {} failure: {}", statisticTopic.getName(), failure)));

        kafkaConsumer.handler(record -> {
            final String topic = record.topic();

            if (StatisticTopic.CLICK.getName().equals(topic)) {
            } else if (StatisticTopic.QUICK.getName().equals(topic)) {
                log.info("Receive record {}", record.value());
            }
        });

        return this;
    }

    @Override
    public Consumer api() {
        log.info("Init api");


        return this;
    }

    @Override
    public Consumer cors() {
        log.info("Init cors");

        router.route().handler(CorsHandler.create("*")

                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.PUT)
                .allowedMethod(HttpMethod.PATCH)
                .allowedMethod(HttpMethod.DELETE)

                .allowedHeader("Content-Type")
                .allowedHeader("Authorization")
        );

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
}
