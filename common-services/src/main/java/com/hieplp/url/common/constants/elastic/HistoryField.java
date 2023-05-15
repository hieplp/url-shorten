package com.hieplp.url.common.constants.elastic;

public enum HistoryField {
    URL_ID("urlid"),
    SOCIAL_TYPE("socialtype"),
    ;

    private final String name;

    HistoryField(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
