package com.hieplp.url.common.payload.request.token;

import com.hieplp.url.common.constants.auth.TokenType;
import com.hieplp.url.common.model.UserModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenerateTokenRequest {
    private UserModel user;
    private TokenType tokenType;
    private Integer activeTime;
}
