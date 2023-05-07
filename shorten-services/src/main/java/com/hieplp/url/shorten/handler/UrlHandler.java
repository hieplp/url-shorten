package com.hieplp.url.shorten.handler;

import com.hieplp.url.common.constants.statistic.StatisticTopic;
import com.hieplp.url.common.model.UrlModel;
import com.hieplp.url.common.payload.request.history.CreateHistoryRequest;

public interface UrlHandler {
    UrlModel saveUrl(UrlModel urlModel);

    void saveUrlHistory(StatisticTopic topic, CreateHistoryRequest request);
}
