package com.hieplp.url.common.model;

import lombok.Data;

@Data
public class UrlModel {
    private Integer urlId;
    private String shortUrl;
    private String longUrl;
    private Byte status;
    private Long createdAt;
    private Long modifiedAt;
}
