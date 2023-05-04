package com.hieplp.url.auth.controller;

import com.google.inject.Inject;
import com.hieplp.url.auth.service.AuthService;
import com.hieplp.url.common.constants.ApiConfig;
import com.hieplp.url.common.controller.BaseController;
import com.hieplp.url.common.router.RouterHandler;
import io.vertx.ext.web.Router;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class AuthController implements BaseController {

    private final RouterHandler routerHandler;
    private final AuthService authService;

    @Override
    public BaseController init(Router router) {
        log.info("Init auth controller");

        router.post(ApiConfig.Auth.LOGIN)
                .handler(routerHandler::postHandler)
                .blockingHandler(routerHandler::anonymousHandler)
                .blockingHandler(ctx -> routerHandler.serviceHandler(ctx, authService::login))
                .blockingHandler(routerHandler::ok);
        router.delete(ApiConfig.Auth.LOGOUT)
                .handler(routerHandler::getHandler)
                .blockingHandler(routerHandler::userHandler)
                .blockingHandler(ctx -> routerHandler.serviceHandler(ctx, authService::logout))
                .blockingHandler(routerHandler::ok);
        router.post(ApiConfig.Auth.REGISTER)
                .handler(routerHandler::postHandler)
                .blockingHandler(routerHandler::anonymousHandler)
                .blockingHandler(ctx -> routerHandler.serviceHandler(ctx, authService::register))
                .blockingHandler(routerHandler::created);
        router.get(ApiConfig.Auth.REFRESH_TOKEN)
                .handler(routerHandler::getHandler)
                .blockingHandler(routerHandler::refreshTokenHandler)
                .blockingHandler(ctx -> routerHandler.serviceHandler(ctx, authService::refreshToken))
                .blockingHandler(routerHandler::ok);


        return this;
    }
}
