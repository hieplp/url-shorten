package com.hieplp.url.common.constants.statistic;

import com.hieplp.url.common.util.States;

public enum StatisticSocialType {
    OTHER(0, "other"),
    MESSENGER(1, "www.messenger.com"),
    FACEBOOK(2, "www.youtube.com"),
    GOOGLE(3, "www.google.com"),
    YOU_TUBE(4, "www.youtube.com"),
    INSTAGRAM(5, "www.instagram.com"),
    ;

    private final Byte code;
    private final String domain;

    StatisticSocialType(Integer code, String domain) {
        this.code = code.byteValue();
        this.domain = domain;
    }

    public static StatisticSocialType safeValueOf(String url) {

        if (States.isNull(url)) {
            return OTHER;
        }

        for (StatisticSocialType type : values()) {
            if (url.contains(type.getDomain())) {
                return type;
            }
        }

        return OTHER;
    }

    public Byte getCode() {
        return this.code;
    }

    public String getDomain() {
        return this.domain;
    }

}
