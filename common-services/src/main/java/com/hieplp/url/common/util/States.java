package com.hieplp.url.common.util;

import java.util.Objects;

public class States {

    public States() {
        throw new IllegalStateException("Utility class: States");
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !str.isEmpty();
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public static boolean isEquals(Object obj1, Object obj2) {
        return Objects.equals(obj1, obj2);
    }

    public static boolean isNotEquals(Object obj1, Object obj2) {
        return !isEquals(obj1, obj2);
    }

    public static boolean isGreaterThan(Long num1, Long num2) {
        return num1 > num2;
    }

    public static boolean isGreaterThanOrEquals(Long num1, Long num2) {
        return num1 >= num2;
    }

    public static boolean isLessThan(Long num1, Long num2) {
        return num1 < num2;
    }

    public static boolean isLessThanOrEquals(Long num1, Long num2) {
        return num1 <= num2;
    }
}
