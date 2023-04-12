package com.hieplp.url.common.payload.request.url;

import com.hieplp.url.common.annotation.NotNull;
import lombok.Data;

@Data
public class CreateUrlRequest {
    private String shortUrl;
    @NotNull
    private String longUrl;
}
