package com.hieplp.url.common.router;

import com.hieplp.url.common.payload.request.CommonRequest;
import com.hieplp.url.common.payload.response.CommonResponse;

@FunctionalInterface
public interface ServiceHandler {
    CommonResponse handle(CommonRequest request);
}
