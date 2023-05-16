package com.hieplp.url.common.constants.elastic;

public enum EsIndex {
    HISTORY("logstash-history");

    private final String name;

    EsIndex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
