/*
 * This file is generated by jOOQ.
 */
package com.hieplp.url.common.repository.user;


import com.hieplp.url.common.repository.user.tables.Password;
import com.hieplp.url.common.repository.user.tables.User;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Shortenuser extends SchemaImpl {

    /**
     * The reference instance of <code>shortenUser</code>
     */
    public static final Shortenuser SHORTENUSER = new Shortenuser();
    private static final long serialVersionUID = 1L;
    /**
     * The table <code>shortenUser.password</code>.
     */
    public final Password PASSWORD = Password.PASSWORD;

    /**
     * The table <code>shortenUser.user</code>.
     */
    public final User USER = User.USER;

    /**
     * No further instances allowed
     */
    private Shortenuser() {
        super("shortenUser", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
                Password.PASSWORD,
                User.USER);
    }
}
