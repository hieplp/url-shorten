package com.hieplp.url.common.util;

import com.hieplp.url.common.annotation.NotNull;
import com.hieplp.url.common.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

@Slf4j
public class ValidationUtil {
    /**
     * Check object is null or not. if null, throw BadRequestException
     *
     * @param object object need to check
     */
    public static void checkNotNullWithAnnotation(Object object) {
        try {
            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);

                if (f.isAnnotationPresent(NotNull.class) && States.isNull(f.get(object))) {
                    throw new BadRequestException(String.format("Field: %s is null", f.getName()));
                }
            }
        } catch (IllegalAccessException e) {
            log.error("Error when check not null with annotation: {}", e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
    }


    /**
     * Check url is valid. if not valid, throw BadRequestException
     *
     * @param url url need to check
     */
    public static void checkUrlIsValid(String url) {
        String regex = "^(http|https)://[a-zA-Z0-9-_.]+.[a-zA-Z]{2,3}(:[0-9]{1,5})?(/.*)?$";
        if (!Pattern.matches(regex, url)) {
            throw new BadRequestException(String.format("Url: %s is not valid", url));
        }
    }
}
