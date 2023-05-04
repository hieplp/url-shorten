package com.hieplp.url.common.handler;

import com.hieplp.url.common.model.TokenModel;
import com.hieplp.url.common.payload.HeaderInformation;
import com.hieplp.url.common.payload.request.token.GenerateTokenRequest;

public interface TokenHandler {
    TokenModel generateToken(GenerateTokenRequest request);

    HeaderInformation validateToken(String token);
}
