package com.hieplp.url.shorten.repository;

import com.hieplp.url.common.model.UrlModel;
import com.hieplp.url.common.payload.request.QueryRequest;
import com.hieplp.url.common.payload.response.QueryResponse;
import com.hieplp.url.common.repository.base.BaseRepository;
import com.hieplp.url.common.repository.url.tables.records.UrlRecord;

public interface UrlRepository extends BaseRepository {
    boolean doesAliasExist(String alias);

    // XXX Public
    UrlModel getUrlModelByPublic(String alias);

    // XXX Auth
    UrlRecord getActiveUrlRecordByOwner(String urlId, String ownerId);

    UrlModel getUrlModelByOwner(String urlId, String ownerId);

    QueryResponse<UrlModel> getUrlsByOwner(QueryRequest request, String ownerId);
}
