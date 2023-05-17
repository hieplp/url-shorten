package com.hieplp.url.statistic.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.aggregations.CalendarInterval;
import co.elastic.clients.elasticsearch._types.aggregations.ExtendedBounds;
import co.elastic.clients.elasticsearch._types.aggregations.FieldDateMath;
import co.elastic.clients.json.JsonData;
import com.google.inject.Inject;
import com.hieplp.url.common.constants.elastic.EsAggregation;
import com.hieplp.url.common.constants.elastic.EsField;
import com.hieplp.url.common.constants.elastic.EsIndex;
import com.hieplp.url.common.constants.statistic.StatisticSocialType;
import com.hieplp.url.common.constants.statusCode.ErrorCode;
import com.hieplp.url.common.constants.statusCode.SuccessCode;
import com.hieplp.url.common.exception.BadRequestException;
import com.hieplp.url.common.model.UrlModel;
import com.hieplp.url.common.payload.request.CommonRequest;
import com.hieplp.url.common.payload.request.statistic.GetStatisticOfSocialMediaRequest;
import com.hieplp.url.common.payload.request.statistic.GetTotalClicksGroupByDateRequest;
import com.hieplp.url.common.payload.response.CommonResponse;
import com.hieplp.url.common.payload.response.statistic.GetStatisticOfSocialMediaResponse;
import com.hieplp.url.common.payload.response.statistic.GetTotalClicksByDateResponse;
import com.hieplp.url.common.payload.response.statistic.GetTotalClicksGroupByDateResponse;
import com.hieplp.url.common.util.DateUtil;
import com.hieplp.url.common.util.JsonUtil;
import com.hieplp.url.common.util.States;
import com.hieplp.url.statistic.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class StatisticServiceImpl implements StatisticService {
    private final static String DATE_PATTERN = "yyyy-MM-dd";
    private final static String MONTH_PATTERN = "yyyy-MM";

    //
    private final ElasticsearchClient elasticClient;

    @Override
    public CommonResponse getStatisticOfSocialMedia(CommonRequest commonRequest) {
        try {
            log.info("Get statistic of social media: {}", commonRequest);

            final var request = JsonUtil.fromJson(commonRequest.getRequest(), GetStatisticOfSocialMediaRequest.class);

            if (States.isEmpty(request.getUrlId())) {
                log.error("UrlId is empty");
                throw new BadRequestException("UrlId is empty");
            }

            // Search in elastic
            var searchResponse = elasticClient.search(s -> s
                            .index(EsIndex.HISTORY.getName())
                            .size(0) // Don't need to return any documents

                            .query(q -> q.match(t -> t.field(EsField.URL_ID.getName()).query(request.getUrlId())))

                            .aggregations(EsAggregation.STATISTIC_OF_SOCIAL_MEDIA.getName(),
                                    a -> a.terms(t -> t.field(EsField.SOCIAL_TYPE.getName())))
                    , UrlModel.class);

            // Get buckets from aggregation
            var buckets = searchResponse.aggregations()
                    .get(EsAggregation.STATISTIC_OF_SOCIAL_MEDIA.getName())
                    .lterms().buckets();

            // Used set default value for social type that doesn't have any clicks
            var socialTypeSet = new HashSet<Long>();
            Arrays.stream(StatisticSocialType.values())
                    .forEach(socialType -> socialTypeSet.add(Long.valueOf(socialType.getCode())));

            // Mapping elastic aggregation to response
            var response = new ArrayList<GetStatisticOfSocialMediaResponse>();
            buckets.array().forEach(bucket -> {
                var item = GetStatisticOfSocialMediaResponse.builder()
                        .socialType(bucket.key())
                        .totalClicks(bucket.docCount())
                        .build();
                response.add(item);

                socialTypeSet.remove(bucket.key());
            });

            // Set default value for social type that doesn't have any clicks
            socialTypeSet.forEach(socialType -> {
                var item = GetStatisticOfSocialMediaResponse.builder()
                        .socialType(socialType)
                        .totalClicks(0L)
                        .build();
                response.add(item);
            });

            // Sort
            response.sort(Comparator.comparing(GetStatisticOfSocialMediaResponse::getSocialType));

            return new CommonResponse(SuccessCode.SUCCESS, response);
        } catch (IOException e) {
            log.error("Error when get statistic of social media: {}", e.getMessage());
            return new CommonResponse(ErrorCode.INTERNAL_SERVER_ERROR, null);
        }
    }

    @Override
    public CommonResponse getTotalClicksByDate(CommonRequest commonRequest) {
        try {
            log.info("Get total clicks by date: {}", commonRequest);

            final var request = JsonUtil.fromJson(commonRequest.getRequest(), GetTotalClicksGroupByDateRequest.class);

            //
            final var fromDate = DateUtil.formatDate(request.getFromDate(), DATE_PATTERN);
            final var toDate = DateUtil.formatDate(request.getToDate(), DATE_PATTERN);

            var searchResponse = elasticClient.search(s -> s
                            .index(EsIndex.HISTORY.getName())
                            .size(0)

                            .query(q -> q.bool(b -> b

                                    // Filter by urlId
                                    .must(m -> m.match(t -> t.field(EsField.URL_ID.getName()).query(request.getUrlId())))

                                    // Filter by date range
                                    .must(m -> m.range(t -> t.field(EsField.CREATED_AT.getName())
                                            .gte(JsonData.of(fromDate))
                                            .lte(JsonData.of(toDate))))
                            ))

                    , Void.class);

            // Mapping elastic hits to response
            var totalHits = searchResponse.hits().total();
            var totalClicks = States.isNull(totalHits) ? 0 : totalHits.value();

            // Mapping to response
            var response = GetTotalClicksByDateResponse.builder()
                    .totalClicks(totalClicks)
                    .build();

            return new CommonResponse(SuccessCode.SUCCESS, response);
        } catch (IOException e) {
            log.error("Error when get statistic of clicks by date: {}", e.getMessage());
            return new CommonResponse(ErrorCode.INTERNAL_SERVER_ERROR, null);
        }
    }

    @Override
    public CommonResponse getTotalClicksGroupByMonth(CommonRequest commonRequest) {
        try {
            log.info("Get total clicks by date: {}", commonRequest);

            final var request = JsonUtil.fromJson(commonRequest.getRequest(), GetTotalClicksGroupByDateRequest.class);

            final var fromDate = DateUtil.formatDate(request.getFromDate(), DATE_PATTERN);
            final var toDate = DateUtil.formatDate(request.getToDate(), DATE_PATTERN);

            var searchResponse = elasticClient.search(s -> s
                            .index(EsIndex.HISTORY.getName())
                            .size(0) // Don't need to return any documents

                            .query(q -> q.bool(b -> b

                                    // Filter by urlId
                                    .must(m -> m.match(t -> t.field(EsField.URL_ID.getName()).query(request.getUrlId())))

                                    // Filter by date range
                                    .must(m -> m.range(t -> t.field(EsField.CREATED_AT.getName())
                                            .gte(JsonData.of(fromDate))
                                            .lte(JsonData.of(toDate))))
                            ))

                            .aggregations(EsAggregation.TOTAL_CLICKS_BY_DATE.getName(), a -> a.dateHistogram(t -> t
                                    .field(EsField.CREATED_AT.getName()) // Group by created_at
                                    .calendarInterval(CalendarInterval.Month) // Group by month
                                    .format(MONTH_PATTERN)

                                    // If month doesn't have any clicks, return 0
                                    .extendedBounds(ExtendedBounds.of(b -> b
                                            .min(FieldDateMath.of(f -> f.value(Double.valueOf(request.getFromDate()))))
                                            .max(FieldDateMath.of(f -> f.value(Double.valueOf(request.getToDate())))))
                                    )
                            ))
                    , Void.class);

            // Get buckets from aggregation
            var buckets = searchResponse.aggregations()
                    .get(EsAggregation.TOTAL_CLICKS_BY_DATE.getName())
                    .dateHistogram().buckets();

            // Mapping elastic aggregation to response
            var response = new ArrayList<GetTotalClicksGroupByDateResponse>();
            buckets.array().forEach(bucket -> {
                var item = GetTotalClicksGroupByDateResponse.builder()
                        .date(bucket.key())
                        .dateAsString(bucket.keyAsString())
                        .totalClicks(bucket.docCount())
                        .build();
                response.add(item);
            });

            return new CommonResponse(SuccessCode.SUCCESS, response);
        } catch (IOException e) {
            log.error("Error when get total clicks by date: {}", e.getMessage());
            return new CommonResponse(ErrorCode.INTERNAL_SERVER_ERROR, null);
        }
    }
}
