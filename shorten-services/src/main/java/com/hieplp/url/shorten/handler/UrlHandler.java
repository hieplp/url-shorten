package com.hieplp.url.shorten.handler;

import com.hieplp.url.common.model.UrlModel;

public interface UrlHandler {
    UrlModel saveUrl(UrlModel urlModel);
}
