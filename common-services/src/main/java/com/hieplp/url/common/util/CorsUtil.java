package com.hieplp.url.common.util;

import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;

public class CorsUtil {
    public static void cors(Router router) {
        // TODO: Allow configurable origins
        router.route().handler(CorsHandler.create()
                .addRelativeOrigin(".*")

                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.PUT)
                .allowedMethod(HttpMethod.PATCH)
                .allowedMethod(HttpMethod.DELETE)

                .allowedHeader("Content-Type")
                .allowedHeader("Authorization")
                .allowedHeader("fromHost")
        );
    }
}
