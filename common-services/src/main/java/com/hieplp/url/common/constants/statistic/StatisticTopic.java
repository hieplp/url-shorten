package com.hieplp.url.common.constants.statistic;

public enum StatisticTopic {
    CLICK("click"),
    QUICK("quickstart"),
    ;
    private final String name;

    StatisticTopic(String name) {
        this.name = name;
    }

    public static StatisticTopic safeValueOf(String name) {
        for (StatisticTopic topic : StatisticTopic.values()) {
            if (topic.getName().equals(name)) {
                return topic;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }
}
