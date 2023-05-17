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
        public static final String PROFILE = PREFIX + "/profile";
    }

    public static class User {
        public static final String PREFIX = "/user";
        public static final String PROFILE = PREFIX + "/profile";
    }

    public static class UserUrl {
        public static final String PREFIX = "/user/url";
    }

    public static class PublicUrl {
        public static final String PREFIX = "/public/url";
    }

    public static class Statistic {
        public static final String PREFIX = "/statistic";
        public static final String SOCIAL_MEDIA = PREFIX + "/social-media";
        public static final String TOTAL_CLICKS_BY_DATE = PREFIX + "/total-clicks-by-date";
        public static final String TOTAL_CLICKS_GROUP_BY_MONTH = PREFIX + "/total-clicks-group-by-month";
    }
}
