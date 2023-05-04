package com.hieplp.url.common.controller;

import io.vertx.ext.web.Router;

public interface BaseController {
    BaseController init(Router router);
}
