package com.hieplp.url.common.handler.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.hieplp.url.common.constants.auth.PasswordKey;
import com.hieplp.url.common.handler.AuthHandler;
import com.hieplp.url.common.repository.url.tables.records.PasswordRecord;
import com.hieplp.url.common.util.GenerateUtil;
import com.hieplp.url.common.util.RsaUtil;
import lombok.extern.slf4j.Slf4j;

import java.security.PrivateKey;
import java.util.Arrays;

@Slf4j
//@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class AuthHandlerImpl implements AuthHandler {

    private final PrivateKey passwordPrivateKey;

    @Inject
    public AuthHandlerImpl(@Named(PasswordKey.PRIVATE) PrivateKey passwordPrivateKey) {
        this.passwordPrivateKey = passwordPrivateKey;
    }

    @Override
    public boolean isPasswordMatched(String inputPassword, byte[] password, byte[] salt) {
        log.info("Check if password is matched");
        byte[] rawPassword = new byte[0];
        try {
            rawPassword = RsaUtil.generatePassword(inputPassword, passwordPrivateKey, salt);
            return Arrays.equals(rawPassword, password);
        } catch (Exception e) {
            log.error("Error when validate password caused by {}", e.getMessage());
            return false;
        } finally {
            // Clear password in memory for security
            Arrays.fill(rawPassword, Byte.MIN_VALUE);
            Arrays.fill(password, Byte.MIN_VALUE);
        }
    }

    @Override
    public PasswordRecord generatePasswordRecord(String userId, String password) {
        log.debug("Get password record by user id: {}", userId);
        byte[] salt = GenerateUtil.generateSalt();
        byte[] rawPassword = RsaUtil.generatePassword(password, passwordPrivateKey, salt);
        return new PasswordRecord(userId, rawPassword, salt);
    }

}
