package com.hieplp.url.common.payload.request.history;

import com.hieplp.url.common.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateHistoryRequest {
    @NotNull
    private String urlId;
    @NotNull
    private String createdBy;
    private String referrer;
}
