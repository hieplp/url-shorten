/*
 * This file is generated by jOOQ.
 */
package com.hieplp.url.common.repository.url.tables.daos;


import com.hieplp.url.common.repository.url.tables.Url;
import com.hieplp.url.common.repository.url.tables.records.UrlRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import java.time.LocalDateTime;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class UrlDao extends DAOImpl<UrlRecord, com.hieplp.url.common.repository.url.tables.pojos.Url, String> {

    /**
     * Create a new UrlDao without any configuration
     */
    public UrlDao() {
        super(Url.URL, com.hieplp.url.common.repository.url.tables.pojos.Url.class);
    }

    /**
     * Create a new UrlDao with an attached configuration
     */
    public UrlDao(Configuration configuration) {
        super(Url.URL, com.hieplp.url.common.repository.url.tables.pojos.Url.class, configuration);
    }

    @Override
    public String getId(com.hieplp.url.common.repository.url.tables.pojos.Url object) {
        return object.getUrlid();
    }

    /**
     * Fetch records that have <code>urlId BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchRangeOfUrlid(String lowerInclusive, String upperInclusive) {
        return fetchRange(Url.URL.URLID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>urlId IN (values)</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchByUrlid(String... values) {
        return fetch(Url.URL.URLID, values);
    }

    /**
     * Fetch a unique record that has <code>urlId = value</code>
     */
    public com.hieplp.url.common.repository.url.tables.pojos.Url fetchOneByUrlid(String value) {
        return fetchOne(Url.URL.URLID, value);
    }

    /**
     * Fetch records that have <code>shortUrl BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchRangeOfShorturl(String lowerInclusive, String upperInclusive) {
        return fetchRange(Url.URL.SHORTURL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>shortUrl IN (values)</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchByShorturl(String... values) {
        return fetch(Url.URL.SHORTURL, values);
    }

    /**
     * Fetch records that have <code>longUrl BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchRangeOfLongurl(String lowerInclusive, String upperInclusive) {
        return fetchRange(Url.URL.LONGURL, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>longUrl IN (values)</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchByLongurl(String... values) {
        return fetch(Url.URL.LONGURL, values);
    }

    /**
     * Fetch records that have <code>alias BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchRangeOfAlias(String lowerInclusive, String upperInclusive) {
        return fetchRange(Url.URL.ALIAS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>alias IN (values)</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchByAlias(String... values) {
        return fetch(Url.URL.ALIAS, values);
    }

    /**
     * Fetch records that have <code>expiredAt BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchRangeOfExpiredat(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Url.URL.EXPIREDAT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>expiredAt IN (values)</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchByExpiredat(LocalDateTime... values) {
        return fetch(Url.URL.EXPIREDAT, values);
    }

    /**
     * Fetch records that have <code>status BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchRangeOfStatus(Byte lowerInclusive, Byte upperInclusive) {
        return fetchRange(Url.URL.STATUS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>status IN (values)</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchByStatus(Byte... values) {
        return fetch(Url.URL.STATUS, values);
    }

    /**
     * Fetch records that have <code>createdBy BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchRangeOfCreatedby(String lowerInclusive, String upperInclusive) {
        return fetchRange(Url.URL.CREATEDBY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>createdBy IN (values)</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchByCreatedby(String... values) {
        return fetch(Url.URL.CREATEDBY, values);
    }

    /**
     * Fetch records that have <code>createdAt BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchRangeOfCreatedat(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Url.URL.CREATEDAT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>createdAt IN (values)</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchByCreatedat(LocalDateTime... values) {
        return fetch(Url.URL.CREATEDAT, values);
    }

    /**
     * Fetch records that have <code>modifiedBy BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchRangeOfModifiedby(String lowerInclusive, String upperInclusive) {
        return fetchRange(Url.URL.MODIFIEDBY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modifiedBy IN (values)</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchByModifiedby(String... values) {
        return fetch(Url.URL.MODIFIEDBY, values);
    }

    /**
     * Fetch records that have <code>modifiedAt BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchRangeOfModifiedat(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Url.URL.MODIFIEDAT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>modifiedAt IN (values)</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchByModifiedat(LocalDateTime... values) {
        return fetch(Url.URL.MODIFIEDAT, values);
    }

    /**
     * Fetch records that have <code>isDeleted BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchRangeOfIsdeleted(Byte lowerInclusive, Byte upperInclusive) {
        return fetchRange(Url.URL.ISDELETED, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>isDeleted IN (values)</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchByIsdeleted(Byte... values) {
        return fetch(Url.URL.ISDELETED, values);
    }

    /**
     * Fetch records that have <code>deletedBy BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchRangeOfDeletedby(String lowerInclusive, String upperInclusive) {
        return fetchRange(Url.URL.DELETEDBY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>deletedBy IN (values)</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchByDeletedby(String... values) {
        return fetch(Url.URL.DELETEDBY, values);
    }

    /**
     * Fetch records that have <code>deletedAt BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchRangeOfDeletedat(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Url.URL.DELETEDAT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>deletedAt IN (values)</code>
     */
    public List<com.hieplp.url.common.repository.url.tables.pojos.Url> fetchByDeletedat(LocalDateTime... values) {
        return fetch(Url.URL.DELETEDAT, values);
    }
}
