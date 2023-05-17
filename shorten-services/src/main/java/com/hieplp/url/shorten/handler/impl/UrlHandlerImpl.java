package com.hieplp.url.shorten.handler.impl;

import com.google.inject.Inject;
import com.hieplp.url.common.constants.statistic.StatisticTopic;
import com.hieplp.url.common.constants.url.UrlIsDeleted;
import com.hieplp.url.common.constants.url.UrlStatus;
import com.hieplp.url.common.handler.KafkaProducerHandler;
import com.hieplp.url.common.model.UrlModel;
import com.hieplp.url.common.payload.request.history.CreateHistoryRequest;
import com.hieplp.url.common.repository.url.tables.records.UrlRecord;
import com.hieplp.url.common.util.DateUtil;
import com.hieplp.url.common.util.GenerateUtil;
import com.hieplp.url.common.util.States;
import com.hieplp.url.shorten.config.ConfigInfo;
import com.hieplp.url.shorten.handler.UrlHandler;
import com.hieplp.url.shorten.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class UrlHandlerImpl implements UrlHandler {
    private final static int DEFAULT_URL_ID_LENGTH = 6;

    private final ConfigInfo configInfo;
    private final UrlRepository urlRepo;
    private final KafkaProducerHandler kafkaProducerHandler;

    @Override
    public UrlModel saveUrl(UrlModel urlModel) {
        log.info("Save url: {}", urlModel);

        var urlRecord = new UrlRecord()
                .setUrlid(GenerateUtil.generate(DEFAULT_URL_ID_LENGTH))
                .setAlias(urlModel.getAlias())
                .setShorturl(String.format("%s/%s", configInfo.getUrlHost(), urlModel.getAlias()))
                .setLongurl(urlModel.getLongUrl())
                .setExpiredat(States.isNull(urlModel.getExpiredAt())
                        ? null
                        : DateUtil.toLocalDateTime(urlModel.getExpiredAt()))
                .setStatus(UrlStatus.ACTIVE.getStatus())
                .setCreatedby(urlModel.getCreatedBy())
                .setCreatedat(LocalDateTime.now())
                .setModifiedby(urlModel.getCreatedBy())
                .setModifiedat(LocalDateTime.now())
                .setIsdeleted(UrlIsDeleted.NOT_DELETED.getValue());

        return urlRepo.saveAndReturn(urlRecord, UrlModel.class);
    }

    @Override
    public void saveUrlHistory(StatisticTopic topic, CreateHistoryRequest request) {
        log.info("Save url history: {}", request);
        kafkaProducerHandler.send(topic.getName(), request.getUrlId(), request);
    }
}
