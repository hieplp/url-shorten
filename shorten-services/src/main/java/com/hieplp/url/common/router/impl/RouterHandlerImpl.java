package com.hieplp.url.common.router.impl;

import com.google.gson.JsonObject;
import com.hieplp.url.common.constants.statusCode.HttpStatusCode;
import com.hieplp.url.common.exception.BadRequestException;
import com.hieplp.url.common.exception.data.DuplicateException;
import com.hieplp.url.common.payload.request.CommonRequest;
import com.hieplp.url.common.payload.response.CommonResponse;
import com.hieplp.url.common.router.RouterHandler;
import com.hieplp.url.common.router.ServiceHandler;
import com.hieplp.url.common.util.JsonUtil;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RouterHandlerImpl implements RouterHandler {

    private final static String REQUEST = "request";
    private final static String RESPONSE = "response";

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
        CommonRequest request = CommonRequest.builder()
                .request(JsonUtil.fromJson(context.getBodyAsString(), JsonObject.class))
                .build();
        context.put(REQUEST, request);
        context.next();
    }

    @Override
    public void serviceHandler(RoutingContext context, ServiceHandler serviceHandler) {
        try {
            CommonRequest request = context.get(REQUEST);
            Object response = serviceHandler.handle(request);
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

        Object response = context.get(RESPONSE);
        context.response()
                .setStatusCode(HttpStatusCode.OK.getValue())
                .putHeader("content-type", "application/json")
                .end(JsonUtil.toJson(response));
    }

    @Override
    public void created(RoutingContext context) {
        if (context.response().ended()) return;

        Object response = context.get(RESPONSE);
        context.response()
                .setStatusCode(HttpStatusCode.CREATED.getValue())
                .putHeader("content-type", "application/json")
                .end(JsonUtil.toJson(response));
    }

    @Override
    public void handleException(RoutingContext context, Exception exception) {
        try {
            throw exception;
        } catch (BadRequestException e) {
            badRequest(context, e);
        } catch (DuplicateException e) {
            conflict(context, e);
        } catch (Exception e) {
            log.error("Error when handle request", e);
            internalError(context);
        }
    }

    @Override
    public void badRequest(RoutingContext context, Exception e) {
        log.error("Bad request: {}", e.getMessage());
        CommonResponse response = CommonResponse.builder()
                .code(String.valueOf(HttpStatusCode.BAD_REQUEST.getValue()))
                .data(e.getMessage())
                .messages(HttpStatusCode.BAD_REQUEST.getDescription())
                .build();

        context.response()
                .setStatusCode(HttpStatusCode.BAD_REQUEST.getValue())
                .putHeader("content-type", "application/json")
                .end(JsonUtil.toJson(response));
    }

    @Override
    public void conflict(RoutingContext context, Exception e) {
        log.error("Conflict: {}", e.getMessage());

        CommonResponse response = CommonResponse.builder()
                .code(String.valueOf(HttpStatusCode.CONFLICT.getValue()))
                .data(e.getMessage())
                .messages(HttpStatusCode.CONFLICT.getDescription())
                .build();

        context.response()
                .setStatusCode(HttpStatusCode.CONFLICT.getValue())
                .putHeader("content-type", "application/json")
                .end(JsonUtil.toJson(response));
    }

    @Override
    public void internalError(RoutingContext context) {
        CommonResponse response = CommonResponse.builder()
                .code(String.valueOf(HttpStatusCode.INTERNAL_SERVER_ERROR.getValue()))
                .messages(HttpStatusCode.INTERNAL_SERVER_ERROR.getDescription())
                .build();

        context.response()
                .setStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR.getValue())
                .putHeader("content-type", "application/json")
                .end(JsonUtil.toJson(response));
    }
}
