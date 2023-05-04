package com.hieplp.url.common.handler;

import com.hieplp.url.common.repository.user.tables.records.PasswordRecord;

public interface AuthHandler {
    boolean isPasswordMatched(String inputPassword, byte[] password, byte[] salt);

    PasswordRecord generatePasswordRecord(String userId, String password);
}
