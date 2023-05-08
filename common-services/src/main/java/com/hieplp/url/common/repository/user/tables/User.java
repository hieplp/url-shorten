/*
 * This file is generated by jOOQ.
 */
package com.hieplp.url.common.repository.user.tables;


import com.hieplp.url.common.repository.user.Keys;
import com.hieplp.url.common.repository.user.Shortenuser;
import com.hieplp.url.common.repository.user.tables.records.UserRecord;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class User extends TableImpl<UserRecord> {

    /**
     * The reference instance of <code>shortenUser.user</code>
     */
    public static final User USER = new User();
    private static final long serialVersionUID = 1L;
    /**
     * The column <code>shortenUser.user.userId</code>.
     */
    public final TableField<UserRecord, String> USERID = createField(DSL.name("userId"), SQLDataType.VARCHAR(100).nullable(false), this, "");
    /**
     * The column <code>shortenUser.user.username</code>.
     */
    public final TableField<UserRecord, String> USERNAME = createField(DSL.name("username"), SQLDataType.VARCHAR(100).defaultValue(DSL.field("NULL", SQLDataType.VARCHAR)), this, "");
    /**
     * The column <code>shortenUser.user.status</code>.
     */
    public final TableField<UserRecord, Byte> STATUS = createField(DSL.name("status"), SQLDataType.TINYINT.defaultValue(DSL.field("NULL", SQLDataType.TINYINT)), this, "");
    /**
     * The column <code>shortenUser.user.createdBy</code>.
     */
    public final TableField<UserRecord, String> CREATEDBY = createField(DSL.name("createdBy"), SQLDataType.VARCHAR(100).defaultValue(DSL.field("NULL", SQLDataType.VARCHAR)), this, "");
    /**
     * The column <code>shortenUser.user.createdAt</code>.
     */
    public final TableField<UserRecord, LocalDateTime> CREATEDAT = createField(DSL.name("createdAt"), SQLDataType.LOCALDATETIME(0).defaultValue(DSL.field("NULL", SQLDataType.LOCALDATETIME)), this, "");
    /**
     * The column <code>shortenUser.user.modifiedBy</code>.
     */
    public final TableField<UserRecord, String> MODIFIEDBY = createField(DSL.name("modifiedBy"), SQLDataType.VARCHAR(100).defaultValue(DSL.field("NULL", SQLDataType.VARCHAR)), this, "");
    /**
     * The column <code>shortenUser.user.modifiedAt</code>.
     */
    public final TableField<UserRecord, LocalDateTime> MODIFIEDAT = createField(DSL.name("modifiedAt"), SQLDataType.LOCALDATETIME(0).defaultValue(DSL.field("NULL", SQLDataType.LOCALDATETIME)), this, "");

    private User(Name alias, Table<UserRecord> aliased) {
        this(alias, aliased, null);
    }

    private User(Name alias, Table<UserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>shortenUser.user</code> table reference
     */
    public User(String alias) {
        this(DSL.name(alias), USER);
    }

    /**
     * Create an aliased <code>shortenUser.user</code> table reference
     */
    public User(Name alias) {
        this(alias, USER);
    }

    /**
     * Create a <code>shortenUser.user</code> table reference
     */
    public User() {
        this(DSL.name("user"), null);
    }

    public <O extends Record> User(Table<O> child, ForeignKey<O, UserRecord> key) {
        super(child, key, USER);
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserRecord> getRecordType() {
        return UserRecord.class;
    }

    @Override
    public Schema getSchema() {
        return Shortenuser.SHORTENUSER;
    }

    @Override
    public UniqueKey<UserRecord> getPrimaryKey() {
        return Keys.KEY_USER_PRIMARY;
    }

    @Override
    public List<UniqueKey<UserRecord>> getKeys() {
        return Arrays.<UniqueKey<UserRecord>>asList(Keys.KEY_USER_PRIMARY);
    }

    @Override
    public User as(String alias) {
        return new User(DSL.name(alias), this);
    }

    @Override
    public User as(Name alias) {
        return new User(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(String name) {
        return new User(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(Name name) {
        return new User(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<String, String, Byte, String, LocalDateTime, String, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }
}