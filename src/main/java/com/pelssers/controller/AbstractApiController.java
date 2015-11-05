package com.pelssers.controller;


import com.pelssers.async.FailableCommandHandler;
import com.pelssers.context.ApiMessages;
import com.pelssers.domain.*;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

public abstract class AbstractApiController implements ApiMessages{

    public String getParameter(RoutingContext routingContext, String name) {
        return routingContext.request().getParam(name);
    }

    public void create(RoutingContext routingContext, Object object) {
        toJson(routingContext, 201, object);
    }

    public void update(RoutingContext routingContext) {
        routingContext.response().setStatusCode(204).end();
    }

    public void get(RoutingContext routingContext, Object object) {
        toJson(routingContext, 200, object);
    }

    public void error(RoutingContext routingContext, HarmonyException e, int statusCode) {
        toJson(routingContext, statusCode, e.liftException());
    }

    public void conflict(RoutingContext routingContext, Conflict e) {
        error(routingContext, e, 409);
    }

    public void notFound(RoutingContext routingContext, ResourceNotFound e) {
        error(routingContext, e, 404);
    }

    public void unprocessable(RoutingContext routingContext, UnprocessableEntity e) {
        error(routingContext, e, 422);
    }
    
    private void toJson(RoutingContext routingContext, int statusCode, Object object) {
        HttpServerResponse response = routingContext.response();
        response.setStatusCode(statusCode);
        response.putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(object));
    }

    public <T> FailableCommandHandler<T, UnprocessableEntity> getPayloadHandler(RoutingContext context, Class<T> clazz,
                      Handler<T> succesHandler) {
        return FailableCommandHandler.from(new GetPayloadCommand<>(context, clazz), succesHandler,
                unprocessableEntity -> unprocessable(context, unprocessableEntity));
    }

}
