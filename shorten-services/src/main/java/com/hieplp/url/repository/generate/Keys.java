/*
 * This file is generated by jOOQ.
 */
package com.hieplp.url.repository.generate;


import com.hieplp.url.repository.generate.tables.Password;
import com.hieplp.url.repository.generate.tables.Url;
import com.hieplp.url.repository.generate.tables.User;
import com.hieplp.url.repository.generate.tables.records.PasswordRecord;
import com.hieplp.url.repository.generate.tables.records.UrlRecord;
import com.hieplp.url.repository.generate.tables.records.UserRecord;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * shortenUrl.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<PasswordRecord> KEY_PASSWORD_PRIMARY = Internal.createUniqueKey(Password.PASSWORD, DSL.name("KEY_password_PRIMARY"), new TableField[]{Password.PASSWORD.USERID}, true);
    public static final UniqueKey<UrlRecord> KEY_URL_PRIMARY = Internal.createUniqueKey(Url.URL, DSL.name("KEY_url_PRIMARY"), new TableField[]{Url.URL.URLID}, true);
    public static final UniqueKey<UserRecord> KEY_USER_PRIMARY = Internal.createUniqueKey(User.USER, DSL.name("KEY_user_PRIMARY"), new TableField[]{User.USER.USERID}, true);
}
