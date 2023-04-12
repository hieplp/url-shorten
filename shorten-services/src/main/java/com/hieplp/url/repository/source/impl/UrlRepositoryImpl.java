package com.hieplp.url.repository.source.impl;

import com.hieplp.url.common.constants.url.UrlStatus;
import com.hieplp.url.common.exception.data.QueryException;
import com.hieplp.url.repository.base.BaseRepositoryImpl;
import com.hieplp.url.repository.base.CustomDSLContext;
import com.hieplp.url.repository.generate.tables.records.UrlRecord;
import com.hieplp.url.repository.source.UrlRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.hieplp.url.repository.generate.Tables.URL;

@Slf4j
public class UrlRepositoryImpl extends BaseRepositoryImpl implements UrlRepository {

    @Override
    public boolean doesShortUrlExist(String shortUrl) {
        try (CustomDSLContext context = getDslContext()) {
            log.info("Check short url: {} exist", shortUrl);
            return context.fetchExists(context.selectFrom(URL)
                    .where(URL.SHORTURL.eq(shortUrl)));
        } catch (Exception e) {
            log.error("Error when check short url exist: {}", e.getMessage());
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public UrlRecord getActiveUrlRecordByShortUrl(String shortUrl) {
        log.info("Get active url record by short url: {}", shortUrl);
        return fetchOneNotNull(URL, URL.SHORTURL.eq(shortUrl).and(URL.STATUS.eq(UrlStatus.ACTIVE.getStatus())));
    }

    @Override
    public List<UrlRecord> getAllActiveUrlRecords() {
        try (CustomDSLContext context = getDslContext()) {
            log.info("Get all active url records");
            return context.selectFrom(URL)
                    .where(URL.STATUS.eq(UrlStatus.ACTIVE.getStatus()))
                    .fetch();
        } catch (Exception e) {
            log.error("Error when get all active url records: {}", e.getMessage());
            throw new QueryException(e.getMessage());
        }
    }
}

