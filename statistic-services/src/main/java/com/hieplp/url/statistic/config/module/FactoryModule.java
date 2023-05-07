package com.hieplp.url.statistic.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.multibindings.MapBinder;
import com.hieplp.url.common.constants.statistic.StatisticTopic;
import com.hieplp.url.statistic.factory.StatisticFactory;
import com.hieplp.url.statistic.stragety.StatisticStrategy;
import com.hieplp.url.statistic.stragety.impl.ClickStatisticStrategy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FactoryModule extends AbstractModule {
    @Override
    protected void configure() {
        log.info("Config factory module");

        MapBinder<StatisticTopic, StatisticStrategy> statisticBinder = MapBinder.newMapBinder(binder(), StatisticTopic.class, StatisticStrategy.class);
        statisticBinder.addBinding(StatisticTopic.CLICK).to(ClickStatisticStrategy.class);
        bind(StatisticFactory.class).in(Scopes.SINGLETON);

    }
}
