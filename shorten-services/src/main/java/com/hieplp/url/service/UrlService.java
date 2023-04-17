package com.hieplp.url.service;

import com.hieplp.url.common.payload.request.CommonRequest;
import com.hieplp.url.common.payload.response.CommonResponse;

public interface UrlService {
    // -------------------------------------------------------------------------
    // XXX Public
    // -------------------------------------------------------------------------
    CommonResponse createUrlByPublic(CommonRequest commonRequest);

    CommonResponse getUrlByPublic(CommonRequest commonRequest);

    // -------------------------------------------------------------------------
    // XXX Auth
    // -------------------------------------------------------------------------
    CommonResponse createUrlByAuth(CommonRequest commonRequest);

    CommonResponse updateUrlByAuth(CommonRequest commonRequest);

    CommonResponse deleteUrlByAuth(CommonRequest commonRequest);

    CommonResponse getUrlByAuth(CommonRequest commonRequest);

    CommonResponse getUrlsByAuth(CommonRequest commonRequest);
}
