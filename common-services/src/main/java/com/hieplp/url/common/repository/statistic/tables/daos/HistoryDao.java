/*
 * This file is generated by jOOQ.
 */
package com.hieplp.url.common.repository.statistic.tables.daos;


import com.hieplp.url.common.repository.statistic.tables.History;
import com.hieplp.url.common.repository.statistic.tables.records.HistoryRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import java.time.LocalDateTime;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class HistoryDao extends DAOImpl<HistoryRecord, com.hieplp.url.common.repository.statistic.tables.pojos.History, String> {

    /**
     * Create a new HistoryDao without any configuration
     */
    public HistoryDao() {
        super(History.HISTORY, com.hieplp.url.common.repository.statistic.tables.pojos.History.class);
    }

    /**
     * Create a new HistoryDao with an attached configuration
     */
    public HistoryDao(Configuration configuration) {
        super(History.HISTORY, com.hieplp.url.common.repository.statistic.tables.pojos.History.class, configuration);
    }

    @Override
    public String getId(com.hieplp.url.common.repository.statistic.tables.pojos.History object) {
        return object.getHistoryid();
    }

    /**
     * Fetch records that have <code>historyId BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.common.repository.statistic.tables.pojos.History> fetchRangeOfHistoryid(String lowerInclusive, String upperInclusive) {
        return fetchRange(History.HISTORY.HISTORYID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>historyId IN (values)</code>
     */
    public List<com.hieplp.url.common.repository.statistic.tables.pojos.History> fetchByHistoryid(String... values) {
        return fetch(History.HISTORY.HISTORYID, values);
    }

    /**
     * Fetch a unique record that has <code>historyId = value</code>
     */
    public com.hieplp.url.common.repository.statistic.tables.pojos.History fetchOneByHistoryid(String value) {
        return fetchOne(History.HISTORY.HISTORYID, value);
    }

    /**
     * Fetch records that have <code>urlId BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.common.repository.statistic.tables.pojos.History> fetchRangeOfUrlid(String lowerInclusive, String upperInclusive) {
        return fetchRange(History.HISTORY.URLID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>urlId IN (values)</code>
     */
    public List<com.hieplp.url.common.repository.statistic.tables.pojos.History> fetchByUrlid(String... values) {
        return fetch(History.HISTORY.URLID, values);
    }

    /**
     * Fetch records that have <code>socialType BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.common.repository.statistic.tables.pojos.History> fetchRangeOfSocialtype(Byte lowerInclusive, Byte upperInclusive) {
        return fetchRange(History.HISTORY.SOCIALTYPE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>socialType IN (values)</code>
     */
    public List<com.hieplp.url.common.repository.statistic.tables.pojos.History> fetchBySocialtype(Byte... values) {
        return fetch(History.HISTORY.SOCIALTYPE, values);
    }

    /**
     * Fetch records that have <code>createdBy BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.common.repository.statistic.tables.pojos.History> fetchRangeOfCreatedby(String lowerInclusive, String upperInclusive) {
        return fetchRange(History.HISTORY.CREATEDBY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>createdBy IN (values)</code>
     */
    public List<com.hieplp.url.common.repository.statistic.tables.pojos.History> fetchByCreatedby(String... values) {
        return fetch(History.HISTORY.CREATEDBY, values);
    }

    /**
     * Fetch records that have <code>createdAt BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.common.repository.statistic.tables.pojos.History> fetchRangeOfCreatedat(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(History.HISTORY.CREATEDAT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>createdAt IN (values)</code>
     */
    public List<com.hieplp.url.common.repository.statistic.tables.pojos.History> fetchByCreatedat(LocalDateTime... values) {
        return fetch(History.HISTORY.CREATEDAT, values);
    }
}
