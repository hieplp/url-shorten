package com.hieplp.url.shorten.controller;

import com.google.inject.Inject;
import com.hieplp.url.shorten.service.UrlService;
import com.hieplp.url.common.constants.ApiConfig;
import com.hieplp.url.common.router.RouterHandler;
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

        // XXX Public
        router.post(ApiConfig.Public.Url.PREFIX)
                .handler(routerHandler::postHandler)
                .blockingHandler(routerHandler::anonymousHandler)
                .blockingHandler(ctx -> routerHandler.serviceHandler(ctx, urlService::createUrlByPublic))
                .blockingHandler(routerHandler::created);

        router.get(ApiConfig.Public.Url.PREFIX + "/:shortUrl")
                .handler(routerHandler::getHandler)
                .blockingHandler(routerHandler::anonymousHandler)
                .blockingHandler(ctx -> routerHandler.serviceHandler(ctx, urlService::getUrlByPublic))
                .blockingHandler(routerHandler::created);

        // XXX User
        router.post(ApiConfig.User.Url.PREFIX)
                .handler(routerHandler::postHandler)
                .blockingHandler(routerHandler::userHandler)
                .blockingHandler(ctx -> routerHandler.serviceHandler(ctx, urlService::createUrlByAuth))
                .blockingHandler(routerHandler::created);

        router.patch(ApiConfig.User.Url.PREFIX)
                .handler(routerHandler::postHandler)
                .blockingHandler(routerHandler::userHandler)
                .blockingHandler(ctx -> routerHandler.serviceHandler(ctx, urlService::updateUrlByAuth))
                .blockingHandler(routerHandler::created);

        router.delete(ApiConfig.User.Url.PREFIX + "/:urlId")
                .handler(routerHandler::getHandler)
                .blockingHandler(routerHandler::userHandler)
                .blockingHandler(ctx -> routerHandler.serviceHandler(ctx, urlService::deleteUrlByAuth))
                .blockingHandler(routerHandler::created);

        router.get(ApiConfig.User.Url.PREFIX + "/:urlId")
                .handler(routerHandler::getHandler)
                .blockingHandler(routerHandler::userHandler)
                .blockingHandler(ctx -> routerHandler.serviceHandler(ctx, urlService::getUrlByAuth))
                .blockingHandler(routerHandler::created);

        router.get(ApiConfig.User.Url.PREFIX)
                .handler(routerHandler::getHandler)
                .blockingHandler(routerHandler::userHandler)
                .blockingHandler(ctx -> routerHandler.serviceHandler(ctx, urlService::getUrlsByAuth))
                .blockingHandler(routerHandler::created);


        return this;
    }
}
