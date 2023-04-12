package com.hieplp.url.common.router;

import io.vertx.ext.web.RoutingContext;

public interface RouterHandler {

    /**
     * Handle request with POST method
     *
     * @param context routing context
     */
    void postHandler(RoutingContext context);

    /**
     * Handle request with GET method
     *
     * @param context routing context
     */
    void getHandler(RoutingContext context);

    /**
     * Handle request with non-authentication
     *
     * @param context routing context
     */
    void anonymousHandler(RoutingContext context);

    /**
     * Handle service. Should be called after postHandler or getHandler
     *
     * @param context        routing context
     * @param serviceHandler service handler
     */
    void serviceHandler(RoutingContext context, ServiceHandler serviceHandler);

    /**
     * Send back a response with status code 200 OK
     *
     * @param context routing context
     */
    void ok(RoutingContext context);

    /**
     * Send back a response with status code 201 Created
     *
     * @param context routing context
     */
    void created(RoutingContext context);

    /**
     * Handle exception.
     *
     * @param context routing context
     * @param e       exception
     */
    void handleException(RoutingContext context, Exception e);

    /**
     * Send back a response with status code 400 Bad Request
     *
     * @param context routing context
     * @param e       exception
     */
    void badRequest(RoutingContext context, Exception e);

    /**
     * Send back a response with status code 409 Conflict
     *
     * @param context routing context
     * @param e       exception
     */
    void conflict(RoutingContext context, Exception e);

    /**
     * Send back a response with status code 500 Internal Server Error
     *
     * @param context routing context
     */
    void internalError(RoutingContext context);
}
