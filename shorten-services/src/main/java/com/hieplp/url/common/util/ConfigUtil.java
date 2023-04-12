package com.hieplp.url.common.util;

import com.hieplp.url.common.exception.InvalidConfigException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;

@Slf4j
public class ConfigUtil {
    public static <T> T loadConfig(String jsonConfig, Class<T> clazz) {
        log.info("Load config: {}", jsonConfig);

        T config = JsonUtil.fromJson(jsonConfig, clazz);
        validateConfigProperties(config, "config");

        return config;
    }

    private static void validateConfigProperties(Object object, String configName) {
        try {
            log.debug("Validate config {} and configName {}", object, configName);

            if (States.isEmpty(configName)) {
                log.debug("{} is null", configName);
                throw new InvalidConfigException("config is null");
            }

            for (Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                String fieldName = f.getName();
                Object value = f.get(object);
                switch (f.get(object).getClass().getSimpleName().toLowerCase()) {
                    case "string":
                        log.debug("config {} in {} is string", fieldName, configName);
                        if (States.isEmpty((String) value)) {
                            throw new InvalidConfigException(String.format("config %s in %s is null", fieldName, configName));
                        }
                        break;
                    case "integer":
                        log.debug("config {} in {} is integer", fieldName, configName);
                        if ((Integer) value < 0) {
                            throw new InvalidConfigException(String.format("config %s in %s is is negative", fieldName, configName));

                        }
                        break;
                    case "boolean":
                        log.debug("config {} in {} is boolean", fieldName, configName);
                        break;
                    case "long":
                        log.debug("config {} in {} is long", fieldName, configName);
                        if ((Long) value < 0) {
                            throw new InvalidConfigException(String.format("config %s in %s is is negative", fieldName, configName));
                        }
                        break;
                    case "double":
                        log.debug("config {} in {} is double", fieldName, configName);
                        if ((Double) value < 0) {
                            throw new InvalidConfigException(String.format("config %s in %s is is negative", fieldName, configName));
                        }
                        break;
                    case "bigdecimal":
                        log.debug("config {} in {} is bigDecimal", fieldName, configName);
                        if (((BigDecimal) value).compareTo(new BigDecimal(0)) < 0) {
                            throw new InvalidConfigException(String.format("config %s in %s is is negative", fieldName, configName));
                        }
                        break;
                    case "biginteger":
                        log.debug("config {} in {} is bigInteger", fieldName, configName);
                        if (((BigInteger) value).compareTo(BigInteger.valueOf(0)) < 0) {
                            throw new InvalidConfigException(String.format("config %s in %s is is negative", fieldName, configName));
                        }
                        break;
                    default:
                        log.debug("config {} in {} is object", fieldName, configName);
                        validateConfigProperties(value, f.getName());
                        break;
                }
            }
        } catch (Exception e) {
            log.error("Error with accessible object cause by {} ", e.getMessage());
            throw new InvalidConfigException(e.getMessage());
        }
    }
}
