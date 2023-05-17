package com.hieplp.url.common.payload.request.url;

import lombok.Data;

@Data
public class GetUrlByPublicRequest {
    private String alias;
    private String referer;
}
