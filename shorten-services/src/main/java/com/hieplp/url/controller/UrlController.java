package com.hieplp.url.controller;

import com.google.inject.Inject;
import com.hieplp.url.common.constants.ApiConfig;
import com.hieplp.url.common.router.RouterHandler;
import com.hieplp.url.service.UrlService;
import io.vertx.ext.web.Router;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class UrlController implements BaseController {
    private final UrlService urlService;
    private final RouterHandler routerHandler;

    @Override
    public UrlController init(Router router) {
        log.info("Init url controller");

        router.get(ApiConfig.Url.PREFIX)
                .handler(routerHandler::getHandler)
                .blockingHandler(routerHandler::anonymousHandler)
                .blockingHandler(ctx -> routerHandler.serviceHandler(ctx, urlService::getUrls))
                .blockingHandler(routerHandler::ok);
        router.get(ApiConfig.Url.PREFIX + "/:shortUrl")
                .handler(routerHandler::getHandler)
                .blockingHandler(routerHandler::anonymousHandler)
                .blockingHandler(ctx -> routerHandler.serviceHandler(ctx, urlService::getUrl))
                .blockingHandler(routerHandler::ok);
        router.post(ApiConfig.Url.PREFIX)
                .handler(routerHandler::postHandler)
                .blockingHandler(routerHandler::anonymousHandler)
                .blockingHandler(ctx -> routerHandler.serviceHandler(ctx, urlService::createUrl))
                .blockingHandler(routerHandler::created);

        return this;
    }
}
