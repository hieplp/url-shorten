/*
 * This file is generated by jOOQ.
 */
package com.hieplp.url.common.repository.user.tables;


import com.hieplp.url.common.repository.user.Keys;
import com.hieplp.url.common.repository.user.Shortenuser;
import com.hieplp.url.common.repository.user.tables.records.PasswordRecord;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Password extends TableImpl<PasswordRecord> {

    /**
     * The reference instance of <code>shortenUser.password</code>
     */
    public static final Password PASSWORD = new Password();
    private static final long serialVersionUID = 1L;
    /**
     * The column <code>shortenUser.password.userId</code>.
     */
    public final TableField<PasswordRecord, String> USERID = createField(DSL.name("userId"), SQLDataType.VARCHAR(100).nullable(false), this, "");
    /**
     * The column <code>shortenUser.password.password</code>.
     */
    public final TableField<PasswordRecord, byte[]> PASSWORD_ = createField(DSL.name("password"), SQLDataType.BINARY(64).defaultValue(DSL.field("NULL", SQLDataType.BINARY)), this, "");
    /**
     * The column <code>shortenUser.password.salt</code>.
     */
    public final TableField<PasswordRecord, byte[]> SALT = createField(DSL.name("salt"), SQLDataType.BINARY(64).defaultValue(DSL.field("NULL", SQLDataType.BINARY)), this, "");

    private Password(Name alias, Table<PasswordRecord> aliased) {
        this(alias, aliased, null);
    }

    private Password(Name alias, Table<PasswordRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>shortenUser.password</code> table reference
     */
    public Password(String alias) {
        this(DSL.name(alias), PASSWORD);
    }

    /**
     * Create an aliased <code>shortenUser.password</code> table reference
     */
    public Password(Name alias) {
        this(alias, PASSWORD);
    }

    /**
     * Create a <code>shortenUser.password</code> table reference
     */
    public Password() {
        this(DSL.name("password"), null);
    }

    public <O extends Record> Password(Table<O> child, ForeignKey<O, PasswordRecord> key) {
        super(child, key, PASSWORD);
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PasswordRecord> getRecordType() {
        return PasswordRecord.class;
    }

    @Override
    public Schema getSchema() {
        return Shortenuser.SHORTENUSER;
    }

    @Override
    public UniqueKey<PasswordRecord> getPrimaryKey() {
        return Keys.KEY_PASSWORD_PRIMARY;
    }

    @Override
    public List<UniqueKey<PasswordRecord>> getKeys() {
        return Arrays.<UniqueKey<PasswordRecord>>asList(Keys.KEY_PASSWORD_PRIMARY);
    }

    @Override
    public Password as(String alias) {
        return new Password(DSL.name(alias), this);
    }

    @Override
    public Password as(Name alias) {
        return new Password(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Password rename(String name) {
        return new Password(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Password rename(Name name) {
        return new Password(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, byte[], byte[]> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}