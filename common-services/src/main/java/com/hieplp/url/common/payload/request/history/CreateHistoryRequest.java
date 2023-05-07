package com.hieplp.url.common.payload.request.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateHistoryRequest {
    private String urlId;
    private String createdBy;
    private String socialTypeAsString;
}
