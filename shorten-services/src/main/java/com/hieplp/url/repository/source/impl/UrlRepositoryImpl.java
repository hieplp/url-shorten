package com.hieplp.url.repository.source.impl;

import com.hieplp.url.common.constants.url.UrlIsDeleted;
import com.hieplp.url.common.constants.url.UrlStatus;
import com.hieplp.url.common.exception.data.QueryException;
import com.hieplp.url.common.model.UrlModel;
import com.hieplp.url.common.payload.request.QueryRequest;
import com.hieplp.url.common.payload.response.QueryResponse;
import com.hieplp.url.repository.base.BaseRepositoryImpl;
import com.hieplp.url.repository.base.CustomDSLContext;
import com.hieplp.url.repository.generate.tables.records.UrlRecord;
import com.hieplp.url.repository.source.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.Field;

import java.time.LocalDateTime;
import java.util.List;

import static com.hieplp.url.repository.generate.Tables.URL;

@Slf4j
public class UrlRepositoryImpl extends BaseRepositoryImpl implements UrlRepository {

    private static final Condition ACTIVE_URL_CONDITION = URL.STATUS.eq(UrlStatus.ACTIVE.getStatus())
            .and(URL.ISDELETED.eq(UrlIsDeleted.NOT_DELETED.getValue()));
    private static final Field<?>[] ACTIVE_PUBLIC_URL_FIELDS = new Field[]{
            URL.URLID, URL.LONGURL, URL.SHORTURL
    };

    @Override
    public boolean doesShortUrlExist(String shortUrl) {
        log.info("Check short url: {} exist", shortUrl);
        return isExistent(URL, URL.SHORTURL.eq(shortUrl)
                .and(ACTIVE_URL_CONDITION)
                .and(URL.EXPIREDAT.isNull().or(URL.EXPIREDAT.greaterThan(LocalDateTime.now()))));
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

    @Override
    public UrlModel getUrlModelByPublic(String shortUrl) {
        log.info("Get url by public with shortUrl: {}", shortUrl);
        return fetchOneNotNull(URL, URL.SHORTURL.eq(shortUrl)
                        .and(ACTIVE_URL_CONDITION)
                        .and(URL.EXPIREDAT.isNull().or(URL.EXPIREDAT.greaterThan(LocalDateTime.now()))),
                UrlModel.class, ACTIVE_PUBLIC_URL_FIELDS);
    }

    @Override
    public UrlRecord getActiveUrlRecordByOwner(String urlId, String ownerId) {
        log.info("Get active url record  by owner with urlId: {} and ownerId: {}", urlId, ownerId);
        return fetchOneNotNull(URL, URL.URLID.eq(urlId).and(URL.CREATEDBY.eq(ownerId)).and(ACTIVE_URL_CONDITION));
    }

    @Override
    public UrlModel getUrlModelByOwner(String urlId, String ownerId) {
        log.info("Get url by owner: {}", urlId);
        return fetchOneNotNull(URL, URL.URLID.eq(urlId)
                .and(URL.CREATEDBY.eq(ownerId))
                .and(URL.ISDELETED.eq(UrlIsDeleted.NOT_DELETED.getValue())), UrlModel.class);
    }

    @Override
    public QueryResponse<UrlModel> getUrlsByOwner(QueryRequest request, String ownerId) {
        log.info("Get urls by owner: {} with request: {}", ownerId, request);
        return fetch(request, URL, URL.CREATEDBY.eq(ownerId).and(URL.ISDELETED.eq(UrlIsDeleted.NOT_DELETED.getValue())), UrlModel.class);
    }
}

