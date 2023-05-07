package com.hieplp.url.shorten.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.inject.Inject;
import com.hieplp.url.common.constants.statistic.StatisticSocialType;
import com.hieplp.url.common.constants.statistic.StatisticTopic;
import com.hieplp.url.common.constants.statusCode.SuccessCode;
import com.hieplp.url.common.constants.url.UrlIsDeleted;
import com.hieplp.url.common.exception.BadRequestException;
import com.hieplp.url.common.exception.data.DuplicateException;
import com.hieplp.url.common.model.UrlModel;
import com.hieplp.url.common.payload.request.CommonRequest;
import com.hieplp.url.common.payload.request.QueryRequest;
import com.hieplp.url.common.payload.request.history.CreateHistoryRequest;
import com.hieplp.url.common.payload.request.url.CreateUrlByAuthRequest;
import com.hieplp.url.common.payload.request.url.CreateUrlByPublicRequest;
import com.hieplp.url.common.payload.request.url.UpdateUrlByAuthRequest;
import com.hieplp.url.common.payload.response.CommonResponse;
import com.hieplp.url.common.payload.response.QueryResponse;
import com.hieplp.url.common.repository.url.tables.records.UrlRecord;
import com.hieplp.url.common.util.*;
import com.hieplp.url.shorten.config.ConfigInfo;
import com.hieplp.url.shorten.handler.UrlHandler;
import com.hieplp.url.shorten.repository.UrlRepository;
import com.hieplp.url.shorten.service.UrlService;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Slf4j
public class UrlServiceImpl implements UrlService {

    private final ConfigInfo configInfo;
    private final UrlRepository urlRepo;
    private final Cache<String, UrlModel> urlCache;
    private final UrlHandler urlHandler;

    @Inject
    public UrlServiceImpl(ConfigInfo configInfo,
                          UrlRepository urlRepo,
                          UrlHandler urlHandler) {
        this.configInfo = configInfo;
        this.urlRepo = urlRepo;
        // Use redis instead of guava cache
        urlCache = CacheBuilder.newBuilder()
                .maximumSize(configInfo.getCacheConfig().getMaximumSize())
                .expireAfterWrite(configInfo.getCacheConfig().getExpireAfterWrite(), TimeUnit.SECONDS)
                .build();
        this.urlHandler = urlHandler;
    }

    @Override
    public CommonResponse createUrlByPublic(CommonRequest commonRequest) {
        log.info("Create url by public with request: {}", commonRequest);

        CreateUrlByPublicRequest request = JsonUtil.fromJson(commonRequest.getRequest(), CreateUrlByPublicRequest.class);

        ValidationUtil.checkNotNullAll(request);
        ValidationUtil.checkUrlIsValid(request.getLongUrl());


        UrlModel urlModel = UrlModel.builder()
                .longUrl(request.getLongUrl())
                .alias(GenerateUtil.generate(configInfo.getAliasLength()))
                .createdBy("public") // TODO: Use deviceId or something else to identify url owner
                .build();
        UrlModel response = urlHandler.saveUrl(urlModel);

        return new CommonResponse(SuccessCode.SUCCESS, response);
    }

    @Override
    public CommonResponse getUrlByPublic(CommonRequest commonRequest) {
        log.info("Get url by public with request: {}", commonRequest);

        UrlModel request = JsonUtil.fromJson(commonRequest.getRequest(), UrlModel.class);
        ValidationUtil.checkNotNull(request);

        UrlModel response = urlCache.getIfPresent(request.getAlias());
        if (States.isNull(response)) {
            response = urlRepo.getUrlModelByPublic(request.getAlias());
            urlCache.put(request.getAlias(), response);
        }

        urlHandler.saveUrlHistory(StatisticTopic.CLICK, CreateHistoryRequest.builder()
                .urlId(response.getUrlId())
                .socialTypeAsString(StatisticSocialType.FACEBOOK.getName()) // FIXME: Hard code
                .createdBy("public") // TODO: Use deviceId or something else to identify url owner
                .build());

        return new CommonResponse(SuccessCode.SUCCESS, response);
    }

    @Override
    public CommonResponse createUrlByAuth(CommonRequest commonRequest) {
        log.info("Create url by auth with request: {}", commonRequest);

        CreateUrlByAuthRequest request = JsonUtil.fromJson(commonRequest.getRequest(), CreateUrlByAuthRequest.class);
        ValidationUtil.checkNotNullWithAnnotation(request);
        ValidationUtil.checkUrlIsValid(request.getLongUrl());

        if (States.isNotBlank(request.getAlias())) {
            if (urlRepo.doesAliasExist(request.getAlias())) {
                log.debug("Alias: {} already exist", request.getAlias());
                throw new DuplicateException(String.format("Alias: %s already exist", request.getAlias()));
            }
        } else {
            request.setAlias(GenerateUtil.generate(configInfo.getAliasLength()));
        }

        if (States.isNotNull(request.getExpiredAt())) {
            final Long currentTime = DateUtil.getCurrentTime();
            if (States.isLessThan(request.getExpiredAt(), currentTime)) {
                log.debug("Expired at: {} is less than current time: {}", request.getExpiredAt(), currentTime);
                throw new BadRequestException(String.format("Expired at: %s is less than current time: %s", request.getExpiredAt(), currentTime));
            }
        }

        UrlModel urlModel = UrlModel.builder()
                .longUrl(request.getLongUrl())
                .alias(request.getAlias())
                .expiredAt(request.getExpiredAt())
                .createdBy(commonRequest.getHeaders().getUserId())
                .build();
        UrlModel response = urlHandler.saveUrl(urlModel);

        return new CommonResponse(SuccessCode.SUCCESS, response);
    }

    @Override
    public CommonResponse updateUrlByAuth(CommonRequest commonRequest) {
        log.info("Update url by auth with request: {}", commonRequest);

        UpdateUrlByAuthRequest request = JsonUtil.fromJson(commonRequest.getRequest(), UpdateUrlByAuthRequest.class);
        ValidationUtil.checkNotNullWithAnnotation(request);

        UrlRecord urlRecord = urlRepo.getActiveUrlRecordByOwner(request.getUrlId(), commonRequest.getHeaders().getUserId());

        final Long currentTime = DateUtil.getCurrentTime();
        if (States.isLessThan(DateUtil.toMilliSeconds(urlRecord.getExpiredat()), currentTime)) {
            log.debug("Url: {} is expired", urlRecord.getUrlid());
            throw new BadRequestException(String.format("Url: %s is expired", urlRecord.getUrlid()));
        }

        if (States.isNotBlank(request.getAlias())
                && States.isNotEquals(request.getAlias(), urlRecord.getShorturl())
                && urlRepo.doesAliasExist(request.getAlias())) {
            log.debug("Alias: {} already exist", request.getAlias());
            throw new DuplicateException(String.format("Alias: %s already exist", request.getAlias()));
        }

        if (States.isNotNull(request.getExpiredAt())
                && States.isLessThan(request.getExpiredAt(), currentTime)) {
            log.debug("Expired at: {} is less than current time: {}", request.getExpiredAt(), currentTime);
            throw new BadRequestException(String.format("Expired at: %s is less than current time: %s", request.getExpiredAt(), currentTime));
        }

        if (States.isNotNull(request.getLongUrl()) && States.isNotEquals(request.getLongUrl(), urlRecord.getLongurl())) {
            ValidationUtil.checkUrlIsValid(request.getLongUrl());
        }

        UrlRecord updatedUrlRecord = new UrlRecord()
                .setUrlid(urlRecord.getUrlid())
                .setAlias(request.getAlias())
                .setShorturl(States.isNull(request.getAlias())
                        ? null : String.format("%s/%s", configInfo.getUrlHost(), request.getAlias()))
                .setLongurl(request.getLongUrl())
                .setExpiredat(States.isNull(request.getExpiredAt())
                        ? null : DateUtil.toLocalDateTime(request.getExpiredAt()))
                .setModifiedat(LocalDateTime.now())
                .setModifiedby(commonRequest.getHeaders().getUserId());
        urlRepo.updateNotNull(updatedUrlRecord);

        UrlModel response = urlRepo.getUrlModelByOwner(request.getUrlId(), commonRequest.getHeaders().getUserId());
        return new CommonResponse(SuccessCode.SUCCESS, response);
    }

    @Override
    public CommonResponse deleteUrlByAuth(CommonRequest commonRequest) {
        log.info("Delete url by auth with request: {}", commonRequest);

        UrlModel request = JsonUtil.fromJson(commonRequest.getRequest(), UrlModel.class);
        ValidationUtil.checkNotNull(request.getUrlId());

        UrlRecord urlRecord = urlRepo.getActiveUrlRecordByOwner(request.getUrlId(), commonRequest.getHeaders().getUserId());
        UrlRecord deletedUrlRecord = new UrlRecord()
                .setUrlid(urlRecord.getUrlid())
                .setIsdeleted(UrlIsDeleted.DELETED.getValue())
                .setDeletedby(commonRequest.getHeaders().getUserId())
                .setDeletedat(LocalDateTime.now());
        urlRepo.updateNotNull(deletedUrlRecord);

        return new CommonResponse(SuccessCode.SUCCESS);
    }

    @Override
    public CommonResponse getUrlByAuth(CommonRequest commonRequest) {
        log.info("Get url by auth with request: {}", commonRequest);

        UrlModel request = JsonUtil.fromJson(commonRequest.getRequest(), UrlModel.class);
        ValidationUtil.checkNotNull(request.getUrlId());

        UrlModel response = urlRepo.getUrlModelByOwner(request.getUrlId(), commonRequest.getHeaders().getUserId());

        return new CommonResponse(SuccessCode.SUCCESS, response);
    }

    @Override
    public CommonResponse getUrlsByAuth(CommonRequest commonRequest) {
        log.info("Get urls by auth with request: {}", commonRequest);
        QueryRequest request = JsonUtil.fromJson(commonRequest.getRequest(), QueryRequest.class);
        QueryResponse<UrlModel> response = urlRepo.getUrlsByOwner(request, commonRequest.getHeaders().getUserId());
        return new CommonResponse(SuccessCode.SUCCESS, response);
    }
}
