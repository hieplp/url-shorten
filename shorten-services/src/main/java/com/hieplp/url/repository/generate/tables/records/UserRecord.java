/*
 * This file is generated by jOOQ.
 */
package com.hieplp.url.repository.generate.tables.records;


import com.hieplp.url.repository.generate.tables.User;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;

import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class UserRecord extends UpdatableRecordImpl<UserRecord> implements Record7<String, String, Byte, String, LocalDateTime, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Create a detached UserRecord
     */
    public UserRecord() {
        super(User.USER);
    }

    /**
     * Create a detached, initialised UserRecord
     */
    public UserRecord(String userid, String username, Byte status, String createdby, LocalDateTime createdat, String modifiedby, LocalDateTime modifiedat) {
        super(User.USER);

        setUserid(userid);
        setUsername(username);
        setStatus(status);
        setCreatedby(createdby);
        setCreatedat(createdat);
        setModifiedby(modifiedby);
        setModifiedat(modifiedat);
    }

    /**
     * Getter for <code>shortenUrl.user.userId</code>.
     */
    public String getUserid() {
        return (String) get(0);
    }

    /**
     * Setter for <code>shortenUrl.user.userId</code>.
     */
    public UserRecord setUserid(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>shortenUrl.user.username</code>.
     */
    public String getUsername() {
        return (String) get(1);
    }

    /**
     * Setter for <code>shortenUrl.user.username</code>.
     */
    public UserRecord setUsername(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>shortenUrl.user.status</code>.
     */
    public Byte getStatus() {
        return (Byte) get(2);
    }

    /**
     * Setter for <code>shortenUrl.user.status</code>.
     */
    public UserRecord setStatus(Byte value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>shortenUrl.user.createdBy</code>.
     */
    public String getCreatedby() {
        return (String) get(3);
    }

    /**
     * Setter for <code>shortenUrl.user.createdBy</code>.
     */
    public UserRecord setCreatedby(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>shortenUrl.user.createdAt</code>.
     */
    public LocalDateTime getCreatedat() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>shortenUrl.user.createdAt</code>.
     */
    public UserRecord setCreatedat(LocalDateTime value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>shortenUrl.user.modifiedBy</code>.
     */
    public String getModifiedby() {
        return (String) get(5);
    }

    /**
     * Setter for <code>shortenUrl.user.modifiedBy</code>.
     */
    public UserRecord setModifiedby(String value) {
        set(5, value);
        return this;
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>shortenUrl.user.modifiedAt</code>.
     */
    public LocalDateTime getModifiedat() {
        return (LocalDateTime) get(6);
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * Setter for <code>shortenUrl.user.modifiedAt</code>.
     */
    public UserRecord setModifiedat(LocalDateTime value) {
        set(6, value);
        return this;
    }

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    @Override
    public Row7<String, String, Byte, String, LocalDateTime, String, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<String, String, Byte, String, LocalDateTime, String, LocalDateTime> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return User.USER.USERID;
    }

    @Override
    public Field<String> field2() {
        return User.USER.USERNAME;
    }

    @Override
    public Field<Byte> field3() {
        return User.USER.STATUS;
    }

    @Override
    public Field<String> field4() {
        return User.USER.CREATEDBY;
    }

    @Override
    public Field<LocalDateTime> field5() {
        return User.USER.CREATEDAT;
    }

    @Override
    public Field<String> field6() {
        return User.USER.MODIFIEDBY;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return User.USER.MODIFIEDAT;
    }

    @Override
    public String component1() {
        return getUserid();
    }

    @Override
    public String component2() {
        return getUsername();
    }

    @Override
    public Byte component3() {
        return getStatus();
    }

    @Override
    public String component4() {
        return getCreatedby();
    }

    @Override
    public LocalDateTime component5() {
        return getCreatedat();
    }

    @Override
    public String component6() {
        return getModifiedby();
    }

    @Override
    public LocalDateTime component7() {
        return getModifiedat();
    }

    @Override
    public String value1() {
        return getUserid();
    }

    @Override
    public String value2() {
        return getUsername();
    }

    @Override
    public Byte value3() {
        return getStatus();
    }

    @Override
    public String value4() {
        return getCreatedby();
    }

    @Override
    public LocalDateTime value5() {
        return getCreatedat();
    }

    @Override
    public String value6() {
        return getModifiedby();
    }

    @Override
    public LocalDateTime value7() {
        return getModifiedat();
    }

    @Override
    public UserRecord value1(String value) {
        setUserid(value);
        return this;
    }

    @Override
    public UserRecord value2(String value) {
        setUsername(value);
        return this;
    }

    @Override
    public UserRecord value3(Byte value) {
        setStatus(value);
        return this;
    }

    @Override
    public UserRecord value4(String value) {
        setCreatedby(value);
        return this;
    }

    @Override
    public UserRecord value5(LocalDateTime value) {
        setCreatedat(value);
        return this;
    }

    @Override
    public UserRecord value6(String value) {
        setModifiedby(value);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    @Override
    public UserRecord value7(LocalDateTime value) {
        setModifiedat(value);
        return this;
    }

    @Override
    public UserRecord values(String value1, String value2, Byte value3, String value4, LocalDateTime value5, String value6, LocalDateTime value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }
}
