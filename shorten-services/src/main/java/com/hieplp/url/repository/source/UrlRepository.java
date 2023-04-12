package com.hieplp.url.repository.source;

import com.hieplp.url.repository.base.BaseRepository;
import com.hieplp.url.repository.generate.tables.records.UrlRecord;

import java.util.List;

public interface UrlRepository extends BaseRepository {
    boolean doesShortUrlExist(String shortUrl);

    UrlRecord getActiveUrlRecordByShortUrl(String shortUrl);

    List<UrlRecord> getAllActiveUrlRecords();
}
