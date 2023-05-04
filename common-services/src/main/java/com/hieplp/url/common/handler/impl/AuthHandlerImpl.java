package com.hieplp.url.common.handler.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.hieplp.url.common.constants.auth.JwtHeader;
import com.hieplp.url.common.constants.auth.PasswordKey;
import com.hieplp.url.common.constants.auth.TokenKey;
import com.hieplp.url.common.exception.auth.InvalidTokenException;
import com.hieplp.url.common.handler.AuthHandler;
import com.hieplp.url.common.model.TokenModel;
import com.hieplp.url.common.model.UserModel;
import com.hieplp.url.common.payload.HeaderInformation;
import com.hieplp.url.common.payload.request.token.GenerateTokenRequest;
import com.hieplp.url.common.repository.url.tables.records.PasswordRecord;
import com.hieplp.url.common.util.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
//@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class AuthHandlerImpl implements AuthHandler {

    private final PrivateKey passwordPrivateKey;
    private final PrivateKey tokenPrivateKey;
    private final PublicKey tokenPublicKey;

    @Inject
    public AuthHandlerImpl(@Named(PasswordKey.PRIVATE) PrivateKey passwordPrivateKey,
                           @Named(TokenKey.PRIVATE) PrivateKey tokenPrivateKey,
                           @Named(TokenKey.PUBLIC) PublicKey tokenPublicKey) {
        this.passwordPrivateKey = passwordPrivateKey;
        this.tokenPrivateKey = tokenPrivateKey;
        this.tokenPublicKey = tokenPublicKey;
    }

    @Override
    public boolean isPasswordMatched(String inputPassword, byte[] password, byte[] salt) {
        log.info("Check if password is matched");
        byte[] rawPassword = new byte[0];
        try {
            rawPassword = RsaUtil.generatePassword(inputPassword, passwordPrivateKey, salt);
            return Arrays.equals(rawPassword, password);
        } catch (Exception e) {
            log.error("Error when validate password caused by {}", e.getMessage());
            return false;
        } finally {
            // Clear password in memory for security
            Arrays.fill(rawPassword, Byte.MIN_VALUE);
            Arrays.fill(password, Byte.MIN_VALUE);
        }
    }

    @Override
    public PasswordRecord generatePasswordRecord(String userId, String password) {
        log.debug("Get password record by user id: {}", userId);
        byte[] salt = GenerateUtil.generateSalt();
        byte[] rawPassword = RsaUtil.generatePassword(password, passwordPrivateKey, salt);
        return new PasswordRecord(userId, rawPassword, salt);
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
