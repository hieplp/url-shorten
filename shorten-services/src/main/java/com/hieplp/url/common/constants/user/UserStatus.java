package com.hieplp.url.common.constants.user;

public enum UserStatus {
    ACTIVE(0),
    ;
    private final Byte status;

    UserStatus(Integer status) {
        this.status = status.byteValue();
    }

    public Byte getStatus() {
        return status;
    }
}
