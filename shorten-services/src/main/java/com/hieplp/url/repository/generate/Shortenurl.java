/*
 * This file is generated by jOOQ.
 */
package com.hieplp.url.repository.generate;


import com.hieplp.url.repository.generate.tables.Password;
import com.hieplp.url.repository.generate.tables.Url;
import com.hieplp.url.repository.generate.tables.User;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Shortenurl extends SchemaImpl {

    /**
     * The reference instance of <code>shortenUrl</code>
     */
    public static final Shortenurl SHORTENURL = new Shortenurl();
    private static final long serialVersionUID = 1L;
    /**
     * The table <code>shortenUrl.password</code>.
     */
    public final Password PASSWORD = Password.PASSWORD;

    /**
     * The table <code>shortenUrl.url</code>.
     */
    public final Url URL = Url.URL;

    /**
     * The table <code>shortenUrl.user</code>.
     */
    public final User USER = User.USER;

    /**
     * No further instances allowed
     */
    private Shortenurl() {
        super("shortenUrl", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
                Password.PASSWORD,
                Url.URL,
                User.USER);
    }
}
