package com.hieplp.url.service;

import com.hieplp.url.common.payload.request.CommonRequest;
import com.hieplp.url.common.payload.response.CommonResponse;

public interface UrlService {
    CommonResponse createUrl(CommonRequest commonRequest);

    CommonResponse getUrl(CommonRequest commonRequest);

    CommonResponse getUrls(CommonRequest commonRequest);
}
