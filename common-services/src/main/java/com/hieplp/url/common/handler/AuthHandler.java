package com.hieplp.url.common.handler;

import com.hieplp.url.common.model.TokenModel;
import com.hieplp.url.common.payload.HeaderInformation;
import com.hieplp.url.common.payload.request.token.GenerateTokenRequest;
import com.hieplp.url.common.repository.url.tables.records.PasswordRecord;

public interface AuthHandler {
    boolean isPasswordMatched(String inputPassword, byte[] password, byte[] salt);

    PasswordRecord generatePasswordRecord(String userId, String password);
}
