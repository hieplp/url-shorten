package com.hieplp.url.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private String userId;
    private String username;
    private Byte status;
    private String createdBy;
    private Long createdAt;
    private String modifiedBy;
    private Long modifiedAt;
}
