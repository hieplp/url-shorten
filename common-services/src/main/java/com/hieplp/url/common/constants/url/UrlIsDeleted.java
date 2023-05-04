package com.hieplp.url.common.constants.url;

public enum UrlIsDeleted {
    NOT_DELETED(0),
    DELETED(1);

    private final Byte value;

    UrlIsDeleted(Integer value) {
        this.value = value.byteValue();
    }

    public Byte getValue() {
        return value;
    }
}
