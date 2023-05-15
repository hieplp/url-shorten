package com.hieplp.url.statistic.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.google.inject.Inject;
import com.hieplp.url.common.constants.elastic.HistoryField;
import com.hieplp.url.common.constants.statusCode.ErrorCode;
import com.hieplp.url.common.constants.statusCode.SuccessCode;
import com.hieplp.url.common.payload.request.CommonRequest;
import com.hieplp.url.common.payload.request.statistic.GetStatisticOfSocialMediaRequest;
import com.hieplp.url.common.payload.response.CommonResponse;
import com.hieplp.url.common.util.JsonUtil;
import com.hieplp.url.statistic.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class StatisticServiceImpl implements StatisticService {

    private final ElasticsearchClient elasticClient;

    @Override
    public CommonResponse getStatisticOfSocialMedia(CommonRequest commonRequest) {
        try {
            log.info("Get statistic of social media: {}", commonRequest);

            GetStatisticOfSocialMediaRequest request = JsonUtil.fromJson(commonRequest.getRequest(), GetStatisticOfSocialMediaRequest.class);


            SearchResponse<Void> response = elasticClient.search(s -> s
                            .index("logstash-history")
                            .query(q -> q.match(t -> t.field(HistoryField.URL_ID.getName()).query(request.getUrlId())))

                            .aggregations("group_by_social_type",
                                    a -> a.terms(t -> t.field(HistoryField.SOCIAL_TYPE.getName())
                                            .size(10)
                                    )
                            )


//                            .aggregations("totalClicks",
//                                    a -> a.valueCount(builder -> builder.field(HistoryField.URL_ID.getName()))
//                            )

//                            .aggregations("social_media",
//                                    a -> a.histogram(h -> h.field(HistoryField.SOCIAL_TYPE.getName()))
//                            )
                    ,
                    Void.class);

            log.warn("Response: {}", response);


            return new CommonResponse(SuccessCode.SUCCESS, null);
        } catch (IOException e) {
            log.error("Error when get statistic of social media: {}", e.getMessage());
            return new CommonResponse(ErrorCode.INTERNAL_SERVER_ERROR, null);
        }
    }
}
