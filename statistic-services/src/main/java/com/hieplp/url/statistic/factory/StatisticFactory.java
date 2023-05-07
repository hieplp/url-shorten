package com.hieplp.url.statistic.factory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.hieplp.url.common.constants.statistic.StatisticTopic;
import com.hieplp.url.statistic.stragety.StatisticStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class StatisticFactory {
    private final Map<StatisticTopic, Provider<StatisticStrategy>> strategyMap;

    public StatisticStrategy getStrategy(StatisticTopic topic) {
        log.debug("Get strategy for topic: {}", topic);
        switch (topic) {
            case CLICK:
                return strategyMap.get(topic).get();
            default:
                log.error("Not found strategy for topic {}", topic);
                throw new IllegalStateException("Unexpected value: " + topic);
        }
    }
}
