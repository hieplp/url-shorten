package com.hieplp.url.statistic.service;

import com.hieplp.url.common.payload.request.CommonRequest;
import com.hieplp.url.common.payload.response.CommonResponse;

public interface StatisticService {
    CommonResponse getStatisticOfSocialMedia(CommonRequest commonRequest);

    CommonResponse getStatisticOfClicksByDate(CommonRequest commonRequest);

    CommonResponse getTotalClicksByDate(CommonRequest commonRequest);
}
