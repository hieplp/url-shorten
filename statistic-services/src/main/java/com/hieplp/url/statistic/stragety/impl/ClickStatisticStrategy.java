package com.hieplp.url.statistic.stragety.impl;

import com.google.inject.Inject;
import com.hieplp.url.common.constants.statistic.StatisticSocialType;
import com.hieplp.url.common.payload.request.history.CreateHistoryRequest;
import com.hieplp.url.common.repository.statistic.tables.records.HistoryRecord;
import com.hieplp.url.common.util.DateUtil;
import com.hieplp.url.common.util.GenerateUtil;
import com.hieplp.url.common.util.ValidationUtil;
import com.hieplp.url.statistic.repository.HistoryRepository;
import com.hieplp.url.statistic.stragety.StatisticStrategy;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class ClickStatisticStrategy implements StatisticStrategy {

    private static final int DEFAULT_HISTORY_ID_LENGTH = 10;

    private final HistoryRepository historyRepo;
    private CreateHistoryRequest request;
    private StatisticSocialType socialType;

    @Override
    public StatisticStrategy validate(Buffer buffer) {
        log.info("Validate click statistic");

        request = Json.decodeValue(buffer, CreateHistoryRequest.class);
        ValidationUtil.checkNotNullAll(request);

        socialType = StatisticSocialType.safeValueOf(request.getSocialTypeAsString());

        return this;
    }

    @Override
    public StatisticStrategy saveHistory() {
        log.info("Save click statistic history");

        var historyRecord = new HistoryRecord()
                .setHistoryid(GenerateUtil.generate(DEFAULT_HISTORY_ID_LENGTH))
                .setUrlid(request.getUrlId())
                .setCreatedby(request.getCreatedBy())
                .setCreatedat(DateUtil.getCurrentLocalDateTime())
                .setSocialtype(socialType.getCode());
        historyRepo.save(historyRecord);

        return this;
    }

    @Override
    public StatisticStrategy executeStatistic() {
        log.info("Execute click statistic");

        // TODO: Social Media - List

        // TODO: Today's total clicks

        //  TODO: Total clicks - Line chart

        return this;
    }
}
