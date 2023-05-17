package com.hieplp.url.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.vertx.core.MultiMap;
import lombok.NonNull;

import java.util.Map;

public class JsonUtil {
    private static Gson gson = new GsonBuilder()
            .disableHtmlEscaping()
            .create();

    public JsonUtil() {
        throw new IllegalStateException("Utility class: JsonUtil");
    }

    public static Gson getGson() {
        if (States.isNull(gson)) {
            gson = new GsonBuilder()
                    .disableHtmlEscaping()
                    .create();
        }

        return gson;
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return getGson().fromJson(json, clazz);
    }

    public static <T> T fromJson(JsonObject json, Class<T> clazz) {
        return getGson().fromJson(json, clazz);
    }

    public static JsonObject fromMultiMap(@NonNull MultiMap map) {
        JsonObject result = new JsonObject();

        for (Map.Entry<String, String> entry : map.entries()) {
            if (map.getAll(entry.getKey()).size() > 1) {
                if (result.has(entry.getKey())) {
                    result.get(entry.getKey()).getAsJsonArray().add(entry.getValue());
                } else {
                    JsonArray array = new JsonArray();
                    array.add(entry.getValue());
                    result.add(entry.getKey(), array);
                }
            } else {
                result.addProperty(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }

    public static String toJson(Object object) {
        return getGson().toJson(object);
    }

    public static <T> T fromMap(Map<?, ?> map, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(map, clazz);
    }

    public static JsonObject merge(JsonObject... jsonObjects) {
        JsonObject result = new JsonObject();

        for (JsonObject jsonObject : jsonObjects) {

            if (States.isNull(jsonObject)) {
                continue;
            }

            for (var entry : jsonObject.entrySet()) {
                result.add(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }
}
