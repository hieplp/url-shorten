package com.hieplp.url.repository.base;

import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.SelectSelectStep;
import org.jooq.Table;

import java.sql.SQLException;

public interface BaseRepository {
    CustomDSLContext getDslContext() throws SQLException;

    /**
     * Save record to database
     *
     * @param record record need to save
     */
    void save(Record record);

    /**
     * Save record to database and return saved object
     *
     * @param record record need to save
     * @param clazz  class of return object
     * @param <T>    type of return object
     * @return return saved object
     */
    <T> T saveAndReturn(Record record, Class<T> clazz);

    /**
     * Update record to database. If field is null, it will not update
     *
     * @param record record need to update
     */
    void updateNotNull(Record record);

    /**
     * Count number of records
     *
     * @param select    select query
     * @param table     table
     * @param condition condition
     * @return number of records
     */
    int count(SelectSelectStep<?> select, Table<?> table, Condition condition);

    /**
     * Count number of records
     *
     * @param table table
     * @return number of records in table
     */
    int count(Table<?> table);

    /**
     * Fetch one record from database. If record is null, throw exception
     *
     * @param table     table
     * @param condition queried condition
     * @param <R>       type of record
     * @return queried record
     */
    <R extends Record> R fetchOneNotNull(Table<R> table, Condition condition);

    /**
     * Fetch one record from database. If record is null, throw exception
     *
     * @param table     table
     * @param condition queried condition
     * @param type      type of return object
     * @param <T>       type of return object
     * @return queried object
     */
    <T> T fetchOneNotNull(Table<?> table, Condition condition, Class<? extends T> type);
}