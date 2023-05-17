package com.hieplp.url.common.router.impl;

import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.hieplp.url.common.constants.auth.HeaderKey;
import com.hieplp.url.common.constants.statusCode.HttpStatusCode;
import com.hieplp.url.common.exception.BadRequestException;
import com.hieplp.url.common.exception.auth.InvalidPasswordException;
import com.hieplp.url.common.exception.auth.InvalidTokenException;
import com.hieplp.url.common.exception.auth.UnauthorizedException;
import com.hieplp.url.common.exception.data.DuplicateException;
import com.hieplp.url.common.exception.data.NotFoundException;
import com.hieplp.url.common.payload.HeaderInformation;
import com.hieplp.url.common.payload.request.CommonRequest;
import com.hieplp.url.common.payload.response.CommonResponse;
import com.hieplp.url.common.router.RouterHandler;
import com.hieplp.url.common.router.ServiceHandler;
import com.hieplp.url.common.util.JsonUtil;
import com.hieplp.url.common.util.States;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.RoutingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class RouterHandlerImpl implements RouterHandler {

    private final static String REQUEST = "request";
    private final static String RESPONSE = "response";

//    private final AuthHandler authHandler;

    @Override
    public void postHandler(RoutingContext context) {
        context.request().bodyHandler(body -> {
            log.info("Post handler with body: {}", body.toString());
            context.setBody(body);
            context.next();
        });
    }

    @Override
    public void getHandler(RoutingContext context) {
        final String body = JsonUtil.toJson(JsonUtil.fromMultiMap(context.request().params()));
        log.debug("Get handler with body: {}", body);
        context.setBody(Buffer.buffer(body));
        context.next();
    }


    @Override
    public void anonymousHandler(RoutingContext context) {
        log.debug("Anonymous handler with body");
        var request = CommonRequest.builder()
                .request(JsonUtil.fromJson(context.body().asString(), JsonObject.class))
                .build();
        context.put(REQUEST, request);
        context.next();
    }

    @Override
    public void headerHandler(RoutingContext context) {
        log.debug("Header handler with body");

        var header = JsonUtil.fromMultiMap(context.request().headers());
        log.warn("Header: {}", header);
        var body = JsonUtil.fromJson(context.body().asString(), JsonObject.class);
        log.warn("Body: {}", body);

        var request = JsonUtil.merge(header, body);
        var commonRequest = CommonRequest.builder()
                .request(request)
                .build();

        context.put(REQUEST, commonRequest);
        context.next();
    }

    @Override
    public void userHandler(RoutingContext context) {
        try {
            log.debug("User handler with body");

            final var userId = context.request().getHeader(HeaderKey.USERID.getName());

            if (States.isBlank(userId)) {
                log.debug("User id is null");
                throw new UnauthorizedException("Invalid user id");
            }

            var headers = HeaderInformation.builder()
                    .userId(userId)
                    .build();
            var request = CommonRequest.builder()
                    .request(JsonUtil.fromJson(context.body().asString(), JsonObject.class))
                    .headers(headers)
                    .build();
            context.put(REQUEST, request);

            context.next();
        } catch (Exception e) {
            handleException(context, e);
        }
    }

    @Override
    public void refreshTokenHandler(RoutingContext context) {
        try {
            log.debug("User handler with body");

            final var token = context.request().getHeader("Authorization");
            if (States.isBlank(token)) {
                throw new UnauthorizedException("Invalid token type");
            }

            var headers = HeaderInformation.builder()
                    .token(token)
                    .build();

            var request = CommonRequest.builder()
                    .request(JsonUtil.fromJson(context.body().asString(), JsonObject.class))
                    .headers(headers)
                    .build();
            context.put(REQUEST, request);

            context.next();
        } catch (Exception e) {
            handleException(context, e);
        }
    }

    @Override
    public void serviceHandler(RoutingContext context, ServiceHandler serviceHandler) {
        try {
            CommonRequest request = context.get(REQUEST);
            var response = serviceHandler.handle(request);
            log.debug("Service handler with response: {}", response);
            context.put(RESPONSE, response);
            context.next();
        } catch (Exception e) {
            handleException(context, e);
        }
    }

    @Override
    public void ok(RoutingContext context) {
        if (context.response().ended()) return;

        var response = context.get(RESPONSE);
        context.response()
                .setStatusCode(HttpStatusCode.OK.getCodeAsInteger())
                .putHeader("content-type", "application/json")
                .end(JsonUtil.toJson(response));
    }

    @Override
    public void created(RoutingContext context) {
        if (context.response().ended()) return;

        var response = context.get(RESPONSE);
        context.response()
                .setStatusCode(HttpStatusCode.CREATED.getCodeAsInteger())
                .putHeader("content-type", "application/json")
                .end(JsonUtil.toJson(response));
    }

    @Override
    public void handleException(RoutingContext context, Exception exception) {
        try {
            throw exception;
        } catch (BadRequestException e) {
            badRequest(context, e);
        } catch (NotFoundException e) {
            notFound(context, e);
        } catch (DuplicateException e) {
            conflict(context, e);
        } catch (InvalidPasswordException e) {
            invalidPassword(context, e);
        } catch (UnauthorizedException | InvalidTokenException e) {
            unauthorized(context, e);
        } catch (Exception e) {
            log.error("Error when handle request", e);
            internalError(context);
        }
    }

    @Override
    public void badRequest(RoutingContext context, Exception e) {
        log.error("Bad request: {}", e.getMessage());
        var response = new CommonResponse(HttpStatusCode.BAD_REQUEST, e.getMessage());
        context.response()
                .setStatusCode(HttpStatusCode.BAD_REQUEST.getCodeAsInteger())
                .putHeader("content-type", "application/json")
                .end(JsonUtil.toJson(response));
    }

    @Override
    public void notFound(RoutingContext context, Exception e) {
        log.error("Not found: {}", e.getMessage());
        var response = new CommonResponse(HttpStatusCode.NOT_FOUND, e.getMessage());
        context.response()
                .setStatusCode(HttpStatusCode.NOT_FOUND.getCodeAsInteger())
                .putHeader("content-type", "application/json")
                .end(JsonUtil.toJson(response));
    }

    @Override
    public void conflict(RoutingContext context, Exception e) {
        log.error("Conflict: {}", e.getMessage());
        var response = new CommonResponse(HttpStatusCode.CONFLICT, e.getMessage());
        context.response()
                .setStatusCode(HttpStatusCode.CONFLICT.getCodeAsInteger())
                .putHeader("content-type", "application/json")
                .end(JsonUtil.toJson(response));
    }

    @Override
    public void unauthorized(RoutingContext context, Exception e) {
        log.error("Unauthorized: {}", e.getMessage());
        var response = new CommonResponse(HttpStatusCode.UNAUTHORIZED, e.getMessage());
        context.response()
                .setStatusCode(HttpStatusCode.UNAUTHORIZED.getCodeAsInteger())
                .putHeader("content-type", "application/json")
                .end(JsonUtil.toJson(response));
    }

    @Override
    public void invalidPassword(RoutingContext context, Exception e) {
        log.error("Invalid password: {}", e.getMessage());
        var response = new CommonResponse(HttpStatusCode.UNAUTHORIZED, e.getMessage());
        context.response()
                .setStatusCode(HttpStatusCode.UNAUTHORIZED.getCodeAsInteger())
                .putHeader("content-type", "application/json")
                .end(JsonUtil.toJson(response));
    }

    @Override
    public void internalError(RoutingContext context) {
        var response = new CommonResponse(HttpStatusCode.INTERNAL_SERVER_ERROR, "Internal error");
        context.response()
                .setStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR.getCodeAsInteger())
                .putHeader("content-type", "application/json")
                .end(JsonUtil.toJson(response));
    }
}
