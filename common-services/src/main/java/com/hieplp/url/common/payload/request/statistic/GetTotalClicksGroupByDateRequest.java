package com.hieplp.url.common.payload.request.statistic;

import lombok.Data;

@Data
public class GetTotalClicksGroupByDateRequest {
    private String urlId;
    private Long fromDate;
    private Long toDate;
}
