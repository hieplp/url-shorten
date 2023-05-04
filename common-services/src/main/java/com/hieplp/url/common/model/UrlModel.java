package com.hieplp.url.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlModel {
    private String urlId;
    private String shortUrl;
    private String longUrl;
    private String alias;
    private Long expiredAt;
    private Byte status;
    private String createdBy;
    private Long createdAt;
    private String modifiedBy;
    private Long modifiedAt;
    private Byte isDeleted;
    private String deletedBy;
    private Long deletedAt;
}
