package com.hieplp.url.gateway.service;

import io.vertx.core.http.HttpClient;
import io.vertx.ext.web.RoutingContext;

public interface RestService {
    void dispatch(RoutingContext context, HttpClient httpClient, String path);
}
