package com.hieplp.url.repository.source;

import com.hieplp.url.repository.base.BaseRepository;
import com.hieplp.url.repository.generate.tables.records.PasswordRecord;

public interface PasswordRepository extends BaseRepository {
    PasswordRecord getPasswordRecordByUserId(String userId);
}
