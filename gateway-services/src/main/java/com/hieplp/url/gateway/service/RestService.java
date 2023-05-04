package com.hieplp.url.gateway.service;

import com.hieplp.url.common.constants.discovery.DiscoveryServiceName;
import io.vertx.core.http.HttpClient;
import io.vertx.ext.web.RoutingContext;

public interface RestService {
    void auth(RoutingContext context);

    void dispatch(RoutingContext context, HttpClient httpClient, String path);

    void dispatch(RoutingContext context, DiscoveryServiceName service);
}
