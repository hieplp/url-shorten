package com.hieplp.url.common.config;

import lombok.Data;

@Data
public class TokenConfig {
    private String privateKey;
    private String publicKey;
    private String issuer;
    private Integer activeTime;
    private Integer timeToLive;
}
