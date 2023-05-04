package com.hieplp.url.shorten.service.impl;

import com.google.inject.Inject;
import com.hieplp.url.common.constants.auth.TokenType;
import com.hieplp.url.common.constants.statusCode.SuccessCode;
import com.hieplp.url.common.constants.user.UserStatus;
import com.hieplp.url.common.exception.BadRequestException;
import com.hieplp.url.common.exception.auth.InvalidPasswordException;
import com.hieplp.url.common.exception.auth.UnauthorizedException;
import com.hieplp.url.common.handler.AuthHandler;
import com.hieplp.url.common.model.TokenModel;
import com.hieplp.url.common.model.UserModel;
import com.hieplp.url.common.payload.HeaderInformation;
import com.hieplp.url.common.payload.request.CommonRequest;
import com.hieplp.url.common.payload.request.auth.LoginRequest;
import com.hieplp.url.common.payload.request.auth.RegisterRequest;
import com.hieplp.url.common.payload.request.token.GenerateTokenRequest;
import com.hieplp.url.common.payload.response.CommonResponse;
import com.hieplp.url.common.payload.response.auth.LoginResponse;
import com.hieplp.url.common.payload.response.auth.RegisterResponse;
import com.hieplp.url.common.repository.url.tables.records.PasswordRecord;
import com.hieplp.url.common.repository.url.tables.records.UserRecord;
import com.hieplp.url.common.util.GenerateUtil;
import com.hieplp.url.common.util.JsonUtil;
import com.hieplp.url.common.util.ValidationUtil;
import com.hieplp.url.shorten.config.ConfigInfo;
import com.hieplp.url.shorten.repository.PasswordRepository;
import com.hieplp.url.shorten.repository.UserRepository;
import com.hieplp.url.shorten.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class AuthServiceImpl implements AuthService {

    private final static int DEFAULT_USER_ID_LENGTH = 10;

    //
    private final ConfigInfo configInfo;
    //
    private final AuthHandler authHandler;
    //
    private final UserRepository userRepo;
    private final PasswordRepository passwordRepo;

    @Override
    public CommonResponse login(CommonRequest commonRequest) {
        try {
            log.info("Login with request: {}", commonRequest);

            LoginRequest request = JsonUtil.fromJson(commonRequest.getRequest(), LoginRequest.class);
            ValidationUtil.checkNotNullAll(request);

            UserModel userModel = userRepo.getUserModelByUsername(request.getUsername());

            if (!UserStatus.ACTIVE.getStatus().equals(userModel.getStatus())) {
                log.error("User: {} is not active", userModel.getUsername());
                throw new UnauthorizedException("User is not active");
            }

            PasswordRecord passwordRecord = passwordRepo.getPasswordRecordByUserId(userModel.getUserId());
            if (!authHandler.isPasswordMatched(request.getPassword(), passwordRecord.getPassword(), passwordRecord.getSalt())) {
                log.error("Password of user: {} is not matched", userModel.getUsername());
                throw new UnauthorizedException("Password is not matched");
            }

            //
            TokenModel accessToken = authHandler.generateToken(GenerateTokenRequest.builder()
                    .user(userModel)
                    .tokenType(TokenType.ACCESS)
                    .activeTime(configInfo.getTokenConfig().getAccessActiveTime())
                    .build());
            TokenModel refreshToken = authHandler.generateToken(GenerateTokenRequest.builder()
                    .user(userModel)
                    .tokenType(TokenType.REFRESH)
                    .activeTime(configInfo.getTokenConfig().getRefreshActiveTime())
                    .build());

            LoginResponse response = LoginResponse.builder()
                    .user(userModel)
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            return new CommonResponse(SuccessCode.SUCCESS, response);
        } catch (UnauthorizedException e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidPasswordException(e.getMessage());
        }
    }

    @Override
    public CommonResponse logout(CommonRequest commonRequest) {
        log.info("Logout with request: {}", commonRequest);
        return null;
    }

    @Override
    public CommonResponse register(CommonRequest commonRequest) {
        log.info("Register with request: {}", commonRequest);

        RegisterRequest request = JsonUtil.fromJson(commonRequest.getRequest(), RegisterRequest.class);
        ValidationUtil.checkNotNullAll(request);

        if (userRepo.doesUsernameExist(request.getUsername())) {
            log.error("Username: {} is existed", request.getUsername());
            throw new BadRequestException("Username is existed");
        }

        //
        final String userId = GenerateUtil.generate(DEFAULT_USER_ID_LENGTH);
        UserRecord userRecord = new UserRecord()
                .setUserid(userId)
                .setUsername(request.getUsername())
                .setStatus(UserStatus.ACTIVE.getStatus())
                .setCreatedby(userId)
                .setCreatedat(LocalDateTime.now())
                .setModifiedby(userId)
                .setModifiedat(LocalDateTime.now());

        PasswordRecord passwordRecord = authHandler.generatePasswordRecord(userId, request.getPassword());

        userRepo.saveWithTransaction(userRecord, passwordRecord);

        //
        UserModel userModel = new UserModel();
        userRecord.into(userModel);

        //
        TokenModel accessToken = authHandler.generateToken(GenerateTokenRequest.builder()
                .user(userModel)
                .tokenType(TokenType.ACCESS)
                .activeTime(configInfo.getTokenConfig().getAccessActiveTime())
                .build());
        TokenModel refreshToken = authHandler.generateToken(GenerateTokenRequest.builder()
                .user(userModel)
                .tokenType(TokenType.REFRESH)
                .activeTime(configInfo.getTokenConfig().getRefreshActiveTime())
                .build());

        RegisterResponse response = RegisterResponse.builder()
                .user(userModel)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        return new CommonResponse(SuccessCode.SUCCESS, response);
    }

    @Override
    public CommonResponse refreshToken(CommonRequest commonRequest) {
        log.info("Refresh token with request: {}", commonRequest);

        final HeaderInformation headers = commonRequest.getHeaders();

        TokenModel accessToken = authHandler.generateToken(GenerateTokenRequest.builder()
                .activeTime(configInfo.getTokenConfig().getAccessActiveTime())
                .tokenType(TokenType.ACCESS)
                .user(UserModel.builder()
                        .userId(headers.getUserId())
                        .build())
                .build());

        return new CommonResponse(SuccessCode.SUCCESS, accessToken);
    }
}
