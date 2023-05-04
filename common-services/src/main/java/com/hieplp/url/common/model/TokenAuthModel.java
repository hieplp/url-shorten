package com.hieplp.url.common.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenAuthModel {
    private Long refreshedAt;
}
