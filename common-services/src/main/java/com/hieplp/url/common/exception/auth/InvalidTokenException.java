package com.hieplp.url.common.exception.auth;

import com.hieplp.url.common.exception.BaseException;

public class InvalidTokenException extends BaseException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
