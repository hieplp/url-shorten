package com.hieplp.url.common.repository.base;

import com.google.inject.Inject;
import com.hieplp.url.common.exception.data.NotFoundException;
import com.hieplp.url.common.exception.data.QueryException;
import com.hieplp.url.common.payload.request.QueryRequest;
import com.hieplp.url.common.payload.response.QueryResponse;
import com.hieplp.url.common.util.States;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.jooq.*;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.regex.Pattern;


@Slf4j
public class BaseRepositoryImpl implements BaseRepository {

    @Inject
    private HikariDataSource dataSource;

    @Override
    public CustomDSLContext getDslContext() throws SQLException {
        log.info("Start get DSL context");
        Settings settings = new Settings();
        settings.setDebugInfoOnStackTrace(true);
        settings.setExecuteLogging(true);
        return new CustomDSLContext(dataSource.getConnection(), SQLDialect.MARIADB, settings);
    }

    @Override
    public void save(Record record) {
        try (CustomDSLContext context = getDslContext()) {
            log.info("Save record");
            context.insertInto(getTable(record))
                    .set(record)
                    .execute();
        } catch (Exception e) {
            log.error("Error when save record: {}", e.getMessage());
            throw new QueryException("Unknown error when save record");
        }
    }

    @Override
    public void saveWithTransaction(Record... records) {
        try (CustomDSLContext context = getDslContext()) {
            log.info("Save record with transaction");
            context.transaction(configuration -> {
                Arrays.stream(records).forEach(record -> {
                    context.insertInto(getTable(record))
                            .set(record)
                            .execute();
                });
            });
        } catch (Exception e) {
            log.error("Error when save record: {}", e.getMessage());
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public <T> T saveAndReturn(Record record, Class<T> clazz) {
        try (CustomDSLContext context = getDslContext()) {
            log.info("Save record and return record");
            return context.insertInto(getTable(record))
                    .set(record)
                    .returning()
                    .fetchOne()
                    .into(clazz);
        } catch (Exception e) {
            log.error("Error when save record: {}", e.getMessage());
            throw new QueryException(e.getMessage());
        }
    }


    @Override
    public void updateNotNull(Record record) {
        try (CustomDSLContext context = getDslContext()) {
            log.info("Update not null");
            Arrays.stream(record.fields()).forEach(field -> {
                record.changed(field, States.isNotNull(record.getValue(field)));
            });
            Table<?> table = getTable(record);
            context.update(table)
                    .set(record)
                    .where(equalKey(table, record))
                    .execute();
        } catch (Exception e) {
            log.info("Error when save record: {}", e.getMessage());
            throw new QueryException("Unknown error when update not null record");
        }
    }

    @Override
    public int count(SelectSelectStep<?> select, Table<?> table, Condition condition) {
        try (CustomDSLContext context = getDslContext()) {
            return context.fetchCount(select.from(table).where(condition));
        } catch (Exception e) {
            log.error("failed to count number of records: " + e.getMessage(), e);
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public int count(Table<?> table) {
        try (CustomDSLContext context = getDslContext()) {
            return context.fetchCount(table);
        } catch (Exception e) {
            log.error("failed to count number of records: " + e.getMessage(), e);
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public <R extends Record> R fetchOneNotNull(Table<R> table, Condition condition) {
        try (CustomDSLContext context = getDslContext()) {
            return context.fetch(table, condition).get(0);
        } catch (IndexOutOfBoundsException e) {
            log.error("Not found record: {}", e.getMessage());
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Error when fetch one not null record: {}", e.getMessage());
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public <T> T fetchOneNotNull(Table<?> table, Condition condition, Class<? extends T> type) {
        try (CustomDSLContext context = getDslContext()) {
            return context.selectFrom(table)
                    .where(condition)
                    .fetchInto(type)
                    .get(0);
        } catch (IndexOutOfBoundsException e) {
            log.error("Not found record: {}", e.getMessage());
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Error when fetch one not null record: {}", e.getMessage());
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public <T> T fetchOneNotNull(Table<?> table, Condition condition, Class<? extends T> type, Field<?>[] fields) {
        try (CustomDSLContext context = getDslContext()) {
            return context.select(fields)
                    .from(table)
                    .where(condition)
                    .fetchInto(type)
                    .get(0);
        } catch (IndexOutOfBoundsException e) {
            log.error("Not found record: {}", e.getMessage());
            throw new NotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Error when fetch one not null record: {}", e.getMessage());
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public boolean isExistent(Table<?> table, Condition condition) {
        try (CustomDSLContext context = getDslContext()) {
            return context.fetchExists(table, condition);
        } catch (Exception e) {
            log.error("Error when fetch exist record: {}", e.getMessage());
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public <T> QueryResponse<T> fetch(QueryRequest request, Table<?> table, Condition condition, Class<? extends T> type) {
        try (CustomDSLContext context = getDslContext()) {
            condition = condition
                    .and(getSearchCondition(request.getSearchBy(), request.getSearchValue()))
                    .and(getFilterCondition(request.getFilterBy(), request.getFilterValue()));
            return QueryResponse.<T>builder()
                    .list(context.selectFrom(table)
                            .where(condition)
                            .orderBy(getSortCondition(request.getOrder(), request.getBy()))
                            .limit(request.getLimit())
                            .offset(request.getFrom())
                            .fetchInto(type))
                    .total(context.fetchCount(table, condition))
                    .build();
        } catch (Exception e) {
            log.error("Error when fetch records: {}", e.getMessage());
            throw new QueryException(e.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // XXX Private
    // -------------------------------------------------------------------------

    protected Condition getSearchCondition(String searchBy, String searchValue) {
        if (States.isBlank(searchBy) || States.isBlank(searchValue)) {
            return DSL.noCondition();
        }

        String[] searchInput = searchBy.split(Pattern.quote("."));
        Field<Object> searchField = DSL.field(DSL.name(searchInput[0], searchInput[1]));
        return searchField.contains(searchValue);
    }

    protected Condition getFilterCondition(String filterBy, String filterValue) {
        if (States.isBlank(filterBy) || States.isBlank(filterValue)) {
            return DSL.noCondition();
        }

        String[] filterInput = filterBy.split(Pattern.quote("."));
        Field<Object> filterField = DSL.field(DSL.name(filterInput[0], filterInput[1]));
        return filterField.eq(filterValue);
    }

    protected SortField<?> getSortCondition(String sortBy, String sortType) {
        if (States.isBlank(sortBy) || States.isBlank(sortType)) {
            return null;
        }

        return "desc".equals(sortType)
                ? DSL.field(sortBy, String.class).desc()
                : DSL.field(sortBy, String.class).asc();
    }

    private Table<?> getTable(Record record) {
        return ((TableRecord<?>) record).getTable();
    }

    private Condition equalKey(Table<?> table, Record record) {
        Condition condition = DSL.trueCondition();
        if (States.isNull(table.getPrimaryKey())) {
            return condition;
        }

        for (TableField field : table.getPrimaryKey().getFields()) {
            condition = condition.and(field.eq(field.getDataType().convert(record.getValue(field.getName()))));
        }
        return condition;
    }
}