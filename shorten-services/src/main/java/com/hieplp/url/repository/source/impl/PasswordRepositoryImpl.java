package com.hieplp.url.repository.source.impl;

import com.hieplp.url.repository.base.BaseRepositoryImpl;
import com.hieplp.url.repository.generate.tables.records.PasswordRecord;
import com.hieplp.url.repository.source.PasswordRepository;
import lombok.extern.slf4j.Slf4j;

import static com.hieplp.url.repository.generate.Tables.PASSWORD;

@Slf4j
public class PasswordRepositoryImpl extends BaseRepositoryImpl implements PasswordRepository {
    @Override
    public PasswordRecord getPasswordRecordByUserId(String userId) {
        log.info("Get password record by userId: {}", userId);
        return fetchOneNotNull(PASSWORD, PASSWORD.USERID.eq(userId));
    }
}
