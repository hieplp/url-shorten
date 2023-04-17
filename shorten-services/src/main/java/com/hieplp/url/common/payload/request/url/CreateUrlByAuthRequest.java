package com.hieplp.url.common.payload.request.url;

import com.hieplp.url.common.annotation.NotNull;
import lombok.Data;

@Data
public class CreateUrlByAuthRequest {
    @NotNull
    private String longUrl;
    private String shortUrl;
    private Long expiredAt;
}
