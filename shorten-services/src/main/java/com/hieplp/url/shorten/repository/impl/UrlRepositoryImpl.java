package com.hieplp.url.shorten.repository.impl;

import com.hieplp.url.shorten.repository.UrlRepository;
import com.hieplp.url.common.constants.url.UrlIsDeleted;
import com.hieplp.url.common.constants.url.UrlStatus;
import com.hieplp.url.common.model.UrlModel;
import com.hieplp.url.common.payload.request.QueryRequest;
import com.hieplp.url.common.payload.response.QueryResponse;
import com.hieplp.url.common.repository.base.BaseRepositoryImpl;
import com.hieplp.url.common.repository.url.tables.records.UrlRecord;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Condition;
import org.jooq.Field;

import java.time.LocalDateTime;

import static com.hieplp.url.common.repository.url.Tables.URL;


@Slf4j
public class UrlRepositoryImpl extends BaseRepositoryImpl implements UrlRepository {

    private static final Condition ACTIVE_URL_CONDITION = URL.STATUS.eq(UrlStatus.ACTIVE.getStatus())
            .and(URL.ISDELETED.eq(UrlIsDeleted.NOT_DELETED.getValue()));
    private static final Field<?>[] ACTIVE_PUBLIC_URL_FIELDS = new Field[]{
            URL.URLID, URL.LONGURL, URL.SHORTURL, URL.ALIAS
    };

    @Override
    public boolean doesAliasExist(String alias) {
        log.info("Check alias: {} exist", alias);
        return isExistent(URL, URL.ALIAS.eq(alias)
                .and(ACTIVE_URL_CONDITION)
                .and(URL.EXPIREDAT.isNull().or(URL.EXPIREDAT.greaterThan(LocalDateTime.now()))));
    }

    @Override
    public UrlModel getUrlModelByPublic(String alias) {
        log.info("Get url by public with alias: {}", alias);
        return fetchOneNotNull(URL, URL.ALIAS.eq(alias)
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

