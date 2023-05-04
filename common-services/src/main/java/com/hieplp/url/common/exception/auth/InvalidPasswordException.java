package com.hieplp.url.common.exception.auth;

import com.hieplp.url.common.exception.BaseException;

public class InvalidPasswordException extends BaseException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
