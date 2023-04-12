package com.hieplp.url.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.inject.Inject;
import com.hieplp.url.common.constants.statusCode.SuccessCode;
import com.hieplp.url.common.constants.url.UrlStatus;
import com.hieplp.url.common.exception.BadRequestException;
import com.hieplp.url.common.exception.data.DuplicateException;
import com.hieplp.url.common.model.UrlModel;
import com.hieplp.url.common.payload.request.CommonRequest;
import com.hieplp.url.common.payload.request.url.CreateUrlRequest;
import com.hieplp.url.common.payload.response.CommonResponse;
import com.hieplp.url.common.util.GenerateUtil;
import com.hieplp.url.common.util.JsonUtil;
import com.hieplp.url.common.util.States;
import com.hieplp.url.common.util.ValidationUtil;
import com.hieplp.url.config.ConfigInfo;
import com.hieplp.url.repository.generate.tables.records.UrlRecord;
import com.hieplp.url.repository.source.UrlRepository;
import com.hieplp.url.service.UrlService;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class UrlServiceImpl implements UrlService {

    private final ConfigInfo configInfo;
    private final UrlRepository urlRepo;
    private final Cache<String, UrlModel> urlCache;

    @Inject
    public UrlServiceImpl(ConfigInfo configInfo, UrlRepository urlRepo) {
        this.configInfo = configInfo;
        this.urlRepo = urlRepo;
        // Use redis instead of guava cache
        urlCache = CacheBuilder.newBuilder()
                .maximumSize(configInfo.getCacheConfig().getMaximumSize())
                .expireAfterWrite(configInfo.getCacheConfig().getExpireAfterWrite(), TimeUnit.SECONDS)
                .build();
    }

    @Override
    public CommonResponse createUrl(CommonRequest commonRequest) {
        log.info("Create url with request: {}", commonRequest);
        CreateUrlRequest request = JsonUtil.fromJson(commonRequest.getRequest(), CreateUrlRequest.class);

        ValidationUtil.checkNotNullWithAnnotation(request);
        ValidationUtil.checkUrlIsValid(request.getLongUrl());

        if (urlRepo.doesShortUrlExist(request.getShortUrl())) {
            log.debug("Short url: {} already exist", request.getShortUrl());
            throw new DuplicateException(String.format("Short url: %s already exist", request.getShortUrl()));
        }

        UrlRecord urlRecord = new UrlRecord()
                .setShorturl(States.isNull(request.getShortUrl())
                        ? GenerateUtil.generate(configInfo.getShortUrlLength())
                        : request.getShortUrl())
                .setLongurl(request.getLongUrl())
                .setStatus(UrlStatus.ACTIVE.getStatus())
                .setCreatedat(LocalDateTime.now())
                .setModifiedat(LocalDateTime.now());
        UrlModel response = urlRepo.saveAndReturn(urlRecord, UrlModel.class);

        return CommonResponse.builder()
                .code(SuccessCode.SUCCESS.getCode())
                .data(response)
                .build();
    }

    @Override
    public CommonResponse getUrl(CommonRequest commonRequest) {
        log.info("Get url with request: {}", commonRequest);
        UrlModel request = JsonUtil.fromJson(commonRequest.getRequest(), UrlModel.class);

        if (States.isNull(request.getShortUrl())) {
            log.debug("Short url is null");
            throw new BadRequestException("Short url is null");
        }

        UrlModel response = urlCache.getIfPresent(request.getShortUrl());
        if (States.isNull(response)) {
            UrlRecord urlRecord = urlRepo.getActiveUrlRecordByShortUrl(request.getShortUrl());

            response = new UrlModel();
            urlRecord.into(response);

            urlCache.put(request.getShortUrl(), response);
        }

        return CommonResponse.builder()
                .response(SuccessCode.SUCCESS)
                .data(response)
                .build();
    }

    @Override
    public CommonResponse getUrls(CommonRequest commonRequest) {
        log.info("Get urls with request: {}", commonRequest);

        List<UrlRecord> urlRecords = urlRepo.getAllActiveUrlRecords();
        List<UrlModel> urlModels = new ArrayList<>();
        for (UrlRecord urlRecord : urlRecords) {
            UrlModel urlModel = new UrlModel();
            urlRecord.into(urlModel);
            urlModels.add(urlModel);
        }

        return CommonResponse.builder()
                .response(SuccessCode.SUCCESS)
                .data(urlModels)
                .build();
    }
}
