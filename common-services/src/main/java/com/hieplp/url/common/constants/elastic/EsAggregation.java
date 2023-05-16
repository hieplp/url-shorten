package com.hieplp.url.common.constants.elastic;

public enum EsAggregation {
    STATISTIC_OF_SOCIAL_MEDIA("group_by_social_type"),
    TOTAL_CLICKS_BY_DATE("group_by_date"),
    ;

    private final String name;

    EsAggregation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
