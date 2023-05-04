package com.hieplp.url.common.constants.auth;

public enum HeaderKey {
    USERID("userId");

    private final String name;

    HeaderKey(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
