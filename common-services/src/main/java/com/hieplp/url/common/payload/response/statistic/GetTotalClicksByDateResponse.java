package com.hieplp.url.common.payload.response.statistic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetTotalClicksByDateResponse {
    private Long totalClicks;
}
