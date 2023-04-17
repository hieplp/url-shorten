package com.hieplp.url.common.util;

public class ConverterUtil {
    private ConverterUtil() {
        throw new IllegalStateException("Utility class: ConverterUtil");
    }

    public static char[] toCharArray(byte[] bytes) {
        // Using the platform's default charset
        String str = new String(bytes);
        return str.toCharArray();
    }
}
