package com.hieplp.url.shorten.service;

import com.hieplp.url.common.payload.request.CommonRequest;
import com.hieplp.url.common.payload.response.CommonResponse;

public interface AuthService {
    CommonResponse login(CommonRequest commonRequest);

    CommonResponse logout(CommonRequest commonRequest);

    CommonResponse register(CommonRequest commonRequest);

    CommonResponse refreshToken(CommonRequest commonRequest);
}
