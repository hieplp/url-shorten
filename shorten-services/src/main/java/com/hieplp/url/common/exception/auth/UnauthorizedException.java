package com.hieplp.url.common.exception.auth;

import com.hieplp.url.common.exception.BaseException;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
