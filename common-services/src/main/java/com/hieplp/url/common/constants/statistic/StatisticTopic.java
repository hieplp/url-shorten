package com.hieplp.url.common.constants.statistic;

public enum StatisticTopic {
    CLICK("click"),
    QUICK("quickstart"),
    ;
    private final String name;

    StatisticTopic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
