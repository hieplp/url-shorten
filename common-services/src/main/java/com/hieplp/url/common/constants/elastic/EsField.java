package com.hieplp.url.common.constants.elastic;

public enum EsField {
    URL_ID("urlid"),
    SOCIAL_TYPE("socialtype"),
    CREATED_AT("createdat"),
    ;

    private final String name;

    EsField(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
