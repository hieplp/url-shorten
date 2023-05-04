package com.hieplp.url.common.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HeaderInformation {
    private String token;
    private String userId;
    private Byte tokenType;
}