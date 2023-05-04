package com.hieplp.url.common.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenModel {
    private String token;
    private Long expiredAt;
    private Long refreshedAt;
}
