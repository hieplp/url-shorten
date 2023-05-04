package com.hieplp.url.gateway.controller;

import com.google.inject.Inject;
import com.hieplp.url.common.constants.ApiConfig;
import com.hieplp.url.common.constants.discovery.DiscoveryServiceName;
import com.hieplp.url.common.controller.BaseController;
import com.hieplp.url.gateway.service.RestService;
import io.vertx.ext.web.Router;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class UserController implements BaseController {

    private final RestService restService;

    @Override
    public BaseController init(Router router) {
        log.info("Init user controller");

        router.route(ApiConfig.User.PREFIX + "/*")
                .handler(restService::auth)
                .handler(ctx -> restService.dispatch(ctx, DiscoveryServiceName.USER));

        return this;
    }
}
