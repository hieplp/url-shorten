package com.hieplp.url.common.payload.response.auth;

import com.hieplp.url.common.model.TokenModel;
import com.hieplp.url.common.model.UserModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponse {
    private UserModel user;
    private TokenModel accessToken;
    private TokenModel refreshToken;
}
