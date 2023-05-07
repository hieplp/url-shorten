package com.hieplp.url.common.constants.statistic;

import com.hieplp.url.common.util.States;

import java.util.Locale;

public enum StatisticSocialType {
    OTHER(0, "other"),
    MESSENGER(1, "messenger"),
    FACEBOOK(2, "facebook"),
    GOOGLE(3, "google"),
    YOU_TUBE(4, "youTube"),
    INSTAGRAM(5, "instagram"),
    ;

    private final Byte code;
    private final String name;

    StatisticSocialType(Integer code, String name) {
        this.code = code.byteValue();
        this.name = name;
    }

    public static StatisticSocialType safeValueOf(String name) {

        if (States.isNull(name)) {
            return OTHER;
        }

        for (StatisticSocialType type : values()) {
            if (type.getName().equals(name.toLowerCase(Locale.ROOT))) {
                return type;
            }
        }

        return OTHER;
    }

    public Byte getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
