package com.hieplp.url.statistic.controller;

import com.google.inject.Inject;
import com.hieplp.url.common.constants.ApiConfig;
import com.hieplp.url.common.controller.BaseController;
import com.hieplp.url.common.router.RouterHandler;
import com.hieplp.url.statistic.service.StatisticService;
import io.vertx.ext.web.Router;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class StatisticController implements BaseController {

    private final RouterHandler routerHandler;
    private final StatisticService statisticService;

    @Override
    public BaseController init(Router router) {
        log.info("Init statistic controller");

        router.get(ApiConfig.Statistic.SOCIAL_MEDIA)
                .handler(routerHandler::getHandler)
                .blockingHandler(routerHandler::anonymousHandler)
                .blockingHandler(ctx -> routerHandler.serviceHandler(ctx, statisticService::getStatisticOfSocialMedia))
                .blockingHandler(routerHandler::ok);

        return this;
    }
}
