package com.hieplp.url.common.payload.request.url;

import com.hieplp.url.common.annotation.NotNull;
import lombok.Data;

@Data
public class UpdateUrlByAuthRequest {
    @NotNull
    private String urlId;
    private String longUrl;
    private String alias;
    private Long expiredAt;
}
