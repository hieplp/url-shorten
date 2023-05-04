package com.hieplp.url.common.repository.base;

import com.hieplp.url.common.util.States;
import lombok.extern.slf4j.Slf4j;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultDSLContext;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class CustomDSLContext extends DefaultDSLContext implements AutoCloseable {
    private final Connection connection;
    private final SQLDialect sqlDialect;
    private final Settings settings;

    public CustomDSLContext(Connection connection, SQLDialect dialect, Settings settings) {
        super(connection, dialect, settings);
        this.connection = connection;
        this.sqlDialect = dialect;
        this.settings = settings;
    }

    @Override
    public void close() {
        if (States.isNull(connection)) {
            return;
        }

        try {
            this.connection.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}