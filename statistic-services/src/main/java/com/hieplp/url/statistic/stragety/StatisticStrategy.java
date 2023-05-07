package com.hieplp.url.statistic.stragety;

import io.vertx.core.buffer.Buffer;

public interface StatisticStrategy {
    /**
     * Validate buffer record from kafka
     *
     * @param buffer buffer record from kafka
     */
    StatisticStrategy validate(Buffer buffer);

    /**
     * Save history to database
     */
    StatisticStrategy saveHistory();

    /**
     * Make statistic data based on history
     */
    StatisticStrategy executeStatistic();
}
