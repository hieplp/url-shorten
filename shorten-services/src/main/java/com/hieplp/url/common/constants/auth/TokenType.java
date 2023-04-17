package com.hieplp.url.common.constants.auth;

public enum TokenType {
    ACCESS(0),
    REFRESH(1);

    private final Byte type;

    TokenType(Integer type) {
        this.type = type.byteValue();
    }

    public Byte getType() {
        return type;
    }
}
