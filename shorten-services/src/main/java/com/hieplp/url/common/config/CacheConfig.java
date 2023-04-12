package com.hieplp.url.common.config;

import lombok.Getter;

@Getter
public class CacheConfig {
    private Integer maximumSize;
    private Integer expireAfterWrite;
}
