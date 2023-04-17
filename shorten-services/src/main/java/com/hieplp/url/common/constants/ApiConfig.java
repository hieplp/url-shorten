package com.hieplp.url.common.constants;

import lombok.Getter;

/**
 * Copyright by Intelin.
 * Creator: Ly Phuoc Hiep
 * Date: 30/03/2023
 * Time: 14:08
 */
@Getter
public class ApiConfig {
    public static class Auth {
        public static final String PREFIX = "/auth";
        public static final String LOGIN = PREFIX + "/login";
        public static final String REGISTER = PREFIX + "/register";
        public static final String LOGOUT = PREFIX + "/logout";
        public static final String REFRESH_TOKEN = PREFIX + "/refresh-token";
    }

    public static class User {
        public static final String PREFIX = "/user";
        public static final String PROFILE = PREFIX + "/profile";

        public static class Url {
            public static final String PREFIX = User.PREFIX + "/url";
        }
    }

    public static class Public {
        public static final String PREFIX = "/public";

        public static class Url {
            public static final String PREFIX = Public.PREFIX + "/url";
        }
    }
}
