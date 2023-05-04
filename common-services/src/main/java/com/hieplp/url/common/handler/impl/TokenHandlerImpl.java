package com.hieplp.url.common.handler.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.hieplp.url.common.constants.auth.JwtHeader;
import com.hieplp.url.common.constants.auth.TokenKey;
import com.hieplp.url.common.exception.auth.InvalidTokenException;
import com.hieplp.url.common.handler.TokenHandler;
import com.hieplp.url.common.model.TokenModel;
import com.hieplp.url.common.model.UserModel;
import com.hieplp.url.common.payload.HeaderInformation;
import com.hieplp.url.common.payload.request.token.GenerateTokenRequest;
import com.hieplp.url.common.util.DateUtil;
import com.hieplp.url.common.util.JsonUtil;
import com.hieplp.url.common.util.States;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TokenHandlerImpl implements TokenHandler {

    private final PrivateKey tokenPrivateKey;
    private final PublicKey tokenPublicKey;

    @Inject
    public TokenHandlerImpl(@Named(TokenKey.PRIVATE) PrivateKey tokenPrivateKey,
                            @Named(TokenKey.PUBLIC) PublicKey tokenPublicKey) {
        this.tokenPrivateKey = tokenPrivateKey;
        this.tokenPublicKey = tokenPublicKey;
    }

    @Override
    public TokenModel generateToken(GenerateTokenRequest request) {
        log.info("Generate token with request: {}", request);

        // Expiration time
        Date currentDate = DateUtil.getCurrentDate();
        Date expiredAt = DateUtil.addSeconds(currentDate, request.getActiveTime());

        // Headers
        Map<String, Object> headers = new HashMap<>();
        headers.put(JwtHeader.USER, request.getUser());
        headers.put(JwtHeader.TYPE, request.getTokenType().getType());

        // JWT builder
        String jwt = Jwts.builder()
                .setHeader(headers)
                .setAudience(request.getUser().getUserId())
                .setExpiration(expiredAt)
                .setIssuedAt(currentDate)
                .signWith(SignatureAlgorithm.RS256, tokenPrivateKey)
                .compact();

        return TokenModel.builder()
                .token(jwt)
                .expiredAt(expiredAt.getTime())
                .build();
    }

    @Override
    public HeaderInformation validateToken(String token) {
        try {
            log.info("Validate token {}", token);

            if (States.isBlank(token)) {
                throw new InvalidTokenException("Token is empty");
            }

            // Verify token
            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) tokenPublicKey, (RSAPrivateKey) tokenPrivateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            verifier.verify(token);

            // Get user information from token
            DecodedJWT decodedJWT = JWT.decode(token);
            UserModel user = JsonUtil.fromMap(decodedJWT.getHeaderClaim(JwtHeader.USER).asMap(), UserModel.class);
            Byte tokenType = decodedJWT.getHeaderClaim(JwtHeader.TYPE).asInt().byteValue();

            return HeaderInformation.builder()
                    .token(token)
                    .tokenType(tokenType)
                    .userId(user.getUserId())
                    .build();

        } catch (Exception e) {
            throw new InvalidTokenException(e.getMessage());
        }
    }
}
