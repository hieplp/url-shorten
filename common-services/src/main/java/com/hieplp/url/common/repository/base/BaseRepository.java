package com.hieplp.url.common.repository.base;

import com.hieplp.url.common.payload.request.QueryRequest;
import com.hieplp.url.common.payload.response.QueryResponse;
import org.jooq.Record;
import org.jooq.*;

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
     * Save record to database with transaction
     *
     * @param records record list
     */
    void saveWithTransaction(Record... records);

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


    /**
     * Fetch one record from database. If record is null, throw exception
     *
     * @param table     table
     * @param condition condition
     * @param type      type of return object
     * @param fields    fields to fetch
     * @param <T>       type of return object
     * @return queried object
     */
    <T> T fetchOneNotNull(Table<?> table, Condition condition, Class<? extends T> type, Field<?>[] fields);

    /**
     * Check if record exist in database
     *
     * @param table     table
     * @param condition queried condition
     * @return true if record exist, otherwise return false
     */
    boolean isExistent(Table<?> table, Condition condition);

    /**
     * Fetch records from database
     *
     * @param request   query request
     * @param table     table to fetch
     * @param condition condition to fetch
     * @param type      type of return object
     * @param <T>       type of return object
     * @return query response
     */
    <T> QueryResponse<T> fetch(QueryRequest request, Table<?> table, Condition condition, Class<? extends T> type);
}