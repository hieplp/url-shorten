package com.hieplp.url.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public DateUtil() {
        throw new IllegalStateException("Utility class: DateUtil");
    }

    public static Date getCurrentDate() {
        return new Date();
    }

    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    public static Long getCurrentTime() {
        return getCurrentDate().getTime();
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return date
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static LocalDateTime toLocalDateTime(Long date) {
        return toLocalDateTime(new Date(date));
    }

    public static Long toMilliSeconds(LocalDateTime localDateTime) {
        return localDateTime
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
    }

    public static Date addSeconds(Long date, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    public static Date addSeconds(Date date, int seconds) {
        return addSeconds(date.getTime(), seconds);
    }

    public static String formatDate(Long date, String pattern) {
        var formatter = DateTimeFormatter.ofPattern(pattern);
        return toLocalDateTime(date).format(formatter);
    }
}
