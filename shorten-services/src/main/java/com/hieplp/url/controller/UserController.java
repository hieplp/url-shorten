package com.hieplp.url.controller;

import com.google.inject.Inject;
import com.hieplp.url.common.constants.ApiConfig;
import com.hieplp.url.common.router.RouterHandler;
import com.hieplp.url.service.UserService;
import io.vertx.ext.web.Router;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class UserController implements BaseController {

    private final RouterHandler routerHandler;
    private final UserService userService;

    @Override
    public BaseController init(Router router) {
        log.info("Init user controller");

        router.get(ApiConfig.User.PROFILE)
                .handler(routerHandler::postHandler)
                .blockingHandler(routerHandler::userHandler)
                .blockingHandler(ctx -> routerHandler.serviceHandler(ctx, userService::getProfileByUser))
                .blockingHandler(routerHandler::ok);

        return this;
    }
}
