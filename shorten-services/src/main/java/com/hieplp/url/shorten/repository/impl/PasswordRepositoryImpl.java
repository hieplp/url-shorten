package com.hieplp.url.shorten.repository.impl;

import com.hieplp.url.common.repository.base.BaseRepositoryImpl;
import com.hieplp.url.common.repository.url.tables.records.PasswordRecord;
import com.hieplp.url.shorten.repository.PasswordRepository;
import lombok.extern.slf4j.Slf4j;

import static com.hieplp.url.common.repository.url.Tables.PASSWORD;


@Slf4j
public class PasswordRepositoryImpl extends BaseRepositoryImpl implements PasswordRepository {
    @Override
    public PasswordRecord getPasswordRecordByUserId(String userId) {
        log.info("Get password record by userId: {}", userId);
        return fetchOneNotNull(PASSWORD, PASSWORD.USERID.eq(userId));
    }
}
