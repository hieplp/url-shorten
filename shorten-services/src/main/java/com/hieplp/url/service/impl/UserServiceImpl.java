package com.hieplp.url.service.impl;

import com.google.inject.Inject;
import com.hieplp.url.common.constants.statusCode.SuccessCode;
import com.hieplp.url.common.model.UserModel;
import com.hieplp.url.common.payload.request.CommonRequest;
import com.hieplp.url.common.payload.response.CommonResponse;
import com.hieplp.url.repository.source.UserRepository;
import com.hieplp.url.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class UserServiceImpl implements UserService {


    private final UserRepository userRepo;

    @Override

    public CommonResponse getProfileByUser(CommonRequest commonRequest) {
        log.info("Get profile by user");
        UserModel userModel = userRepo.getUserModelByUserId(commonRequest.getHeaders().getUserId());
        return new CommonResponse(SuccessCode.SUCCESS, userModel);
    }
}
