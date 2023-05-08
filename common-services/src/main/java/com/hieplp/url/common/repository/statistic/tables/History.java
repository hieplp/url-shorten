/*
 * This file is generated by jOOQ.
 */
package com.hieplp.url.common.repository.statistic.tables;


import com.hieplp.url.common.repository.statistic.Keys;
import com.hieplp.url.common.repository.statistic.Shortenstatistic;
import com.hieplp.url.common.repository.statistic.tables.records.HistoryRecord;
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
public class History extends TableImpl<HistoryRecord> {

    /**
     * The reference instance of <code>shortenStatistic.history</code>
     */
    public static final History HISTORY = new History();
    private static final long serialVersionUID = 1L;
    /**
     * The column <code>shortenStatistic.history.historyId</code>.
     */
    public final TableField<HistoryRecord, String> HISTORYID = createField(DSL.name("historyId"), SQLDataType.VARCHAR(255).nullable(false), this, "");
    /**
     * The column <code>shortenStatistic.history.urlId</code>.
     */
    public final TableField<HistoryRecord, String> URLID = createField(DSL.name("urlId"), SQLDataType.VARCHAR(100).defaultValue(DSL.field("NULL", SQLDataType.VARCHAR)), this, "");
    /**
     * The column <code>shortenStatistic.history.socialType</code>.
     */
    public final TableField<HistoryRecord, Byte> SOCIALTYPE = createField(DSL.name("socialType"), SQLDataType.TINYINT.defaultValue(DSL.field("NULL", SQLDataType.TINYINT)), this, "");
    /**
     * The column <code>shortenStatistic.history.createdBy</code>.
     */
    public final TableField<HistoryRecord, String> CREATEDBY = createField(DSL.name("createdBy"), SQLDataType.VARCHAR(50).defaultValue(DSL.field("NULL", SQLDataType.VARCHAR)), this, "");
    /**
     * The column <code>shortenStatistic.history.createdAt</code>.
     */
    public final TableField<HistoryRecord, LocalDateTime> CREATEDAT = createField(DSL.name("createdAt"), SQLDataType.LOCALDATETIME(0).defaultValue(DSL.field("NULL", SQLDataType.LOCALDATETIME)), this, "");

    private History(Name alias, Table<HistoryRecord> aliased) {
        this(alias, aliased, null);
    }

    private History(Name alias, Table<HistoryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>shortenStatistic.history</code> table reference
     */
    public History(String alias) {
        this(DSL.name(alias), HISTORY);
    }

    /**
     * Create an aliased <code>shortenStatistic.history</code> table reference
     */
    public History(Name alias) {
        this(alias, HISTORY);
    }

    /**
     * Create a <code>shortenStatistic.history</code> table reference
     */
    public History() {
        this(DSL.name("history"), null);
    }

    public <O extends Record> History(Table<O> child, ForeignKey<O, HistoryRecord> key) {
        super(child, key, HISTORY);
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<HistoryRecord> getRecordType() {
        return HistoryRecord.class;
    }

    @Override
    public Schema getSchema() {
        return Shortenstatistic.SHORTENSTATISTIC;
    }

    @Override
    public UniqueKey<HistoryRecord> getPrimaryKey() {
        return Keys.KEY_HISTORY_PRIMARY;
    }

    @Override
    public List<UniqueKey<HistoryRecord>> getKeys() {
        return Arrays.<UniqueKey<HistoryRecord>>asList(Keys.KEY_HISTORY_PRIMARY);
    }

    @Override
    public History as(String alias) {
        return new History(DSL.name(alias), this);
    }

    @Override
    public History as(Name alias) {
        return new History(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public History rename(String name) {
        return new History(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public History rename(Name name) {
        return new History(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<String, String, Byte, String, LocalDateTime> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}