/*
 * This file is generated by jOOQ.
 */
package com.hieplp.url.common.repository.url.tables.records;


import com.hieplp.url.common.repository.url.tables.Url;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record13;
import org.jooq.Row13;
import org.jooq.impl.UpdatableRecordImpl;

import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class UrlRecord extends UpdatableRecordImpl<UrlRecord> implements Record13<String, String, String, String, LocalDateTime, Byte, String, LocalDateTime, String, LocalDateTime, Byte, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Create a detached UrlRecord
     */
    public UrlRecord() {
        super(Url.URL);
    }

    /**
     * Create a detached, initialised UrlRecord
     */
    public UrlRecord(String urlid, String shorturl, String longurl, String alias, LocalDateTime expiredat, Byte status, String createdby, LocalDateTime createdat, String modifiedby, LocalDateTime modifiedat, Byte isdeleted, String deletedby, LocalDateTime deletedat) {
        super(Url.URL);

        setUrlid(urlid);
        setShorturl(shorturl);
        setLongurl(longurl);
        setAlias(alias);
        setExpiredat(expiredat);
        setStatus(status);
        setCreatedby(createdby);
        setCreatedat(createdat);
        setModifiedby(modifiedby);
        setModifiedat(modifiedat);
        setIsdeleted(isdeleted);
        setDeletedby(deletedby);
        setDeletedat(deletedat);
    }

    /**
     * Getter for <code>shortenUrl.url.urlId</code>.
     */
    public String getUrlid() {
        return (String) get(0);
    }

    /**
     * Setter for <code>shortenUrl.url.urlId</code>.
     */
    public UrlRecord setUrlid(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>shortenUrl.url.shortUrl</code>.
     */
    public String getShorturl() {
        return (String) get(1);
    }

    /**
     * Setter for <code>shortenUrl.url.shortUrl</code>.
     */
    public UrlRecord setShorturl(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>shortenUrl.url.longUrl</code>.
     */
    public String getLongurl() {
        return (String) get(2);
    }

    /**
     * Setter for <code>shortenUrl.url.longUrl</code>.
     */
    public UrlRecord setLongurl(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>shortenUrl.url.alias</code>.
     */
    public String getAlias() {
        return (String) get(3);
    }

    /**
     * Setter for <code>shortenUrl.url.alias</code>.
     */
    public UrlRecord setAlias(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>shortenUrl.url.expiredAt</code>.
     */
    public LocalDateTime getExpiredat() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>shortenUrl.url.expiredAt</code>.
     */
    public UrlRecord setExpiredat(LocalDateTime value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>shortenUrl.url.status</code>.
     */
    public Byte getStatus() {
        return (Byte) get(5);
    }

    /**
     * Setter for <code>shortenUrl.url.status</code>.
     */
    public UrlRecord setStatus(Byte value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>shortenUrl.url.createdBy</code>.
     */
    public String getCreatedby() {
        return (String) get(6);
    }

    /**
     * Setter for <code>shortenUrl.url.createdBy</code>.
     */
    public UrlRecord setCreatedby(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>shortenUrl.url.createdAt</code>.
     */
    public LocalDateTime getCreatedat() {
        return (LocalDateTime) get(7);
    }

    /**
     * Setter for <code>shortenUrl.url.createdAt</code>.
     */
    public UrlRecord setCreatedat(LocalDateTime value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>shortenUrl.url.modifiedBy</code>.
     */
    public String getModifiedby() {
        return (String) get(8);
    }

    /**
     * Setter for <code>shortenUrl.url.modifiedBy</code>.
     */
    public UrlRecord setModifiedby(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>shortenUrl.url.modifiedAt</code>.
     */
    public LocalDateTime getModifiedat() {
        return (LocalDateTime) get(9);
    }

    /**
     * Setter for <code>shortenUrl.url.modifiedAt</code>.
     */
    public UrlRecord setModifiedat(LocalDateTime value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>shortenUrl.url.isDeleted</code>.
     */
    public Byte getIsdeleted() {
        return (Byte) get(10);
    }

    /**
     * Setter for <code>shortenUrl.url.isDeleted</code>.
     */
    public UrlRecord setIsdeleted(Byte value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>shortenUrl.url.deletedBy</code>.
     */
    public String getDeletedby() {
        return (String) get(11);
    }

    /**
     * Setter for <code>shortenUrl.url.deletedBy</code>.
     */
    public UrlRecord setDeletedby(String value) {
        set(11, value);
        return this;
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>shortenUrl.url.deletedAt</code>.
     */
    public LocalDateTime getDeletedat() {
        return (LocalDateTime) get(12);
    }

    // -------------------------------------------------------------------------
    // Record13 type implementation
    // -------------------------------------------------------------------------

    /**
     * Setter for <code>shortenUrl.url.deletedAt</code>.
     */
    public UrlRecord setDeletedat(LocalDateTime value) {
        set(12, value);
        return this;
    }

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    @Override
    public Row13<String, String, String, String, LocalDateTime, Byte, String, LocalDateTime, String, LocalDateTime, Byte, String, LocalDateTime> fieldsRow() {
        return (Row13) super.fieldsRow();
    }

    @Override
    public Row13<String, String, String, String, LocalDateTime, Byte, String, LocalDateTime, String, LocalDateTime, Byte, String, LocalDateTime> valuesRow() {
        return (Row13) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Url.URL.URLID;
    }

    @Override
    public Field<String> field2() {
        return Url.URL.SHORTURL;
    }

    @Override
    public Field<String> field3() {
        return Url.URL.LONGURL;
    }

    @Override
    public Field<String> field4() {
        return Url.URL.ALIAS;
    }

    @Override
    public Field<LocalDateTime> field5() {
        return Url.URL.EXPIREDAT;
    }

    @Override
    public Field<Byte> field6() {
        return Url.URL.STATUS;
    }

    @Override
    public Field<String> field7() {
        return Url.URL.CREATEDBY;
    }

    @Override
    public Field<LocalDateTime> field8() {
        return Url.URL.CREATEDAT;
    }

    @Override
    public Field<String> field9() {
        return Url.URL.MODIFIEDBY;
    }

    @Override
    public Field<LocalDateTime> field10() {
        return Url.URL.MODIFIEDAT;
    }

    @Override
    public Field<Byte> field11() {
        return Url.URL.ISDELETED;
    }

    @Override
    public Field<String> field12() {
        return Url.URL.DELETEDBY;
    }

    @Override
    public Field<LocalDateTime> field13() {
        return Url.URL.DELETEDAT;
    }

    @Override
    public String component1() {
        return getUrlid();
    }

    @Override
    public String component2() {
        return getShorturl();
    }

    @Override
    public String component3() {
        return getLongurl();
    }

    @Override
    public String component4() {
        return getAlias();
    }

    @Override
    public LocalDateTime component5() {
        return getExpiredat();
    }

    @Override
    public Byte component6() {
        return getStatus();
    }

    @Override
    public String component7() {
        return getCreatedby();
    }

    @Override
    public LocalDateTime component8() {
        return getCreatedat();
    }

    @Override
    public String component9() {
        return getModifiedby();
    }

    @Override
    public LocalDateTime component10() {
        return getModifiedat();
    }

    @Override
    public Byte component11() {
        return getIsdeleted();
    }

    @Override
    public String component12() {
        return getDeletedby();
    }

    @Override
    public LocalDateTime component13() {
        return getDeletedat();
    }

    @Override
    public String value1() {
        return getUrlid();
    }

    @Override
    public String value2() {
        return getShorturl();
    }

    @Override
    public String value3() {
        return getLongurl();
    }

    @Override
    public String value4() {
        return getAlias();
    }

    @Override
    public LocalDateTime value5() {
        return getExpiredat();
    }

    @Override
    public Byte value6() {
        return getStatus();
    }

    @Override
    public String value7() {
        return getCreatedby();
    }

    @Override
    public LocalDateTime value8() {
        return getCreatedat();
    }

    @Override
    public String value9() {
        return getModifiedby();
    }

    @Override
    public LocalDateTime value10() {
        return getModifiedat();
    }

    @Override
    public Byte value11() {
        return getIsdeleted();
    }

    @Override
    public String value12() {
        return getDeletedby();
    }

    @Override
    public LocalDateTime value13() {
        return getDeletedat();
    }

    @Override
    public UrlRecord value1(String value) {
        setUrlid(value);
        return this;
    }

    @Override
    public UrlRecord value2(String value) {
        setShorturl(value);
        return this;
    }

    @Override
    public UrlRecord value3(String value) {
        setLongurl(value);
        return this;
    }

    @Override
    public UrlRecord value4(String value) {
        setAlias(value);
        return this;
    }

    @Override
    public UrlRecord value5(LocalDateTime value) {
        setExpiredat(value);
        return this;
    }

    @Override
    public UrlRecord value6(Byte value) {
        setStatus(value);
        return this;
    }

    @Override
    public UrlRecord value7(String value) {
        setCreatedby(value);
        return this;
    }

    @Override
    public UrlRecord value8(LocalDateTime value) {
        setCreatedat(value);
        return this;
    }

    @Override
    public UrlRecord value9(String value) {
        setModifiedby(value);
        return this;
    }

    @Override
    public UrlRecord value10(LocalDateTime value) {
        setModifiedat(value);
        return this;
    }

    @Override
    public UrlRecord value11(Byte value) {
        setIsdeleted(value);
        return this;
    }

    @Override
    public UrlRecord value12(String value) {
        setDeletedby(value);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    @Override
    public UrlRecord value13(LocalDateTime value) {
        setDeletedat(value);
        return this;
    }

    @Override
    public UrlRecord values(String value1, String value2, String value3, String value4, LocalDateTime value5, Byte value6, String value7, LocalDateTime value8, String value9, LocalDateTime value10, Byte value11, String value12, LocalDateTime value13) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        return this;
    }
}