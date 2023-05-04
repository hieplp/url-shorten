package com.hieplp.url.common.constants.url;

public enum UrlStatus {
    INACTIVE((byte) 0),
    ACTIVE((byte) 1);

    private final Byte status;

    UrlStatus(Byte status) {
        this.status = status;
    }

    public Byte getStatus() {
        return status;
    }
}
