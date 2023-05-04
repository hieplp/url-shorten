package com.hieplp.url.common.constants.statusCode;

public enum ErrorCode implements ResponseCode {
    BAD_REQUEST("4000", "Bad request"),
    INTERNAL_SERVER_ERROR("5000", "Internal server error"),
    ;

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
