/*
 * This file is generated by jOOQ.
 */
package com.hieplp.url.common.repository.url;


import com.hieplp.url.common.repository.url.tables.Url;
import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables in shortenUrl.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index URL_URL_SHORTURL_STATUS_INDEX = Internal.createIndex(DSL.name("url_shortUrl_status_index"), Url.URL, new OrderField[]{Url.URL.SHORTURL, Url.URL.STATUS}, false);
}
