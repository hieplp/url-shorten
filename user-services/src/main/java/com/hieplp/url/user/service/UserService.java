package com.hieplp.url.user.service;

import com.hieplp.url.common.payload.request.CommonRequest;
import com.hieplp.url.common.payload.response.CommonResponse;

public interface UserService {
    CommonResponse getProfileByUser(CommonRequest commonRequest);
}
