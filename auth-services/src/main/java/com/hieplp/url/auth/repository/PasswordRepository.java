package com.hieplp.url.auth.repository;


import com.hieplp.url.common.repository.base.BaseRepository;
import com.hieplp.url.common.repository.url.tables.records.PasswordRecord;

public interface PasswordRepository extends BaseRepository {
    PasswordRecord getPasswordRecordByUserId(String userId);
}
