package com.hieplp.url.shorten.controller;

import io.vertx.ext.web.Router;

public interface BaseController {
    BaseController init(Router router);
}
