/*
 * This file is generated by jOOQ.
 */
package com.hieplp.url.repository.generate.tables.daos;


import com.hieplp.url.repository.generate.tables.Password;
import com.hieplp.url.repository.generate.tables.records.PasswordRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;

import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class PasswordDao extends DAOImpl<PasswordRecord, com.hieplp.url.repository.generate.tables.pojos.Password, String> {

    /**
     * Create a new PasswordDao without any configuration
     */
    public PasswordDao() {
        super(Password.PASSWORD, com.hieplp.url.repository.generate.tables.pojos.Password.class);
    }

    /**
     * Create a new PasswordDao with an attached configuration
     */
    public PasswordDao(Configuration configuration) {
        super(Password.PASSWORD, com.hieplp.url.repository.generate.tables.pojos.Password.class, configuration);
    }

    @Override
    public String getId(com.hieplp.url.repository.generate.tables.pojos.Password object) {
        return object.getUserid();
    }

    /**
     * Fetch records that have <code>userId BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.repository.generate.tables.pojos.Password> fetchRangeOfUserid(String lowerInclusive, String upperInclusive) {
        return fetchRange(Password.PASSWORD.USERID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>userId IN (values)</code>
     */
    public List<com.hieplp.url.repository.generate.tables.pojos.Password> fetchByUserid(String... values) {
        return fetch(Password.PASSWORD.USERID, values);
    }

    /**
     * Fetch a unique record that has <code>userId = value</code>
     */
    public com.hieplp.url.repository.generate.tables.pojos.Password fetchOneByUserid(String value) {
        return fetchOne(Password.PASSWORD.USERID, value);
    }

    /**
     * Fetch records that have <code>password BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.repository.generate.tables.pojos.Password> fetchRangeOfPassword(byte[] lowerInclusive, byte[] upperInclusive) {
        return fetchRange(Password.PASSWORD.PASSWORD_, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>password IN (values)</code>
     */
    public List<com.hieplp.url.repository.generate.tables.pojos.Password> fetchByPassword(byte[]... values) {
        return fetch(Password.PASSWORD.PASSWORD_, values);
    }

    /**
     * Fetch records that have <code>salt BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hieplp.url.repository.generate.tables.pojos.Password> fetchRangeOfSalt(byte[] lowerInclusive, byte[] upperInclusive) {
        return fetchRange(Password.PASSWORD.SALT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>salt IN (values)</code>
     */
    public List<com.hieplp.url.repository.generate.tables.pojos.Password> fetchBySalt(byte[]... values) {
        return fetch(Password.PASSWORD.SALT, values);
    }
}
