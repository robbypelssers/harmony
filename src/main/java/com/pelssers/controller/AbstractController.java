package com.pelssers.controller;


import com.pelssers.HarmonyException;
import com.pelssers.HarmonyExceptionMessage;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

public abstract class AbstractController {

    public void get(RoutingContext routingContext, Object object) {
        toJson(routingContext.response(), object);
    }

    public void conflict(RoutingContext routingContext, HarmonyException e) {
        toJson(routingContext.response().setStatusCode(409), new HarmonyExceptionMessage(e));
    }

    public void create(RoutingContext routingContext, Object object) {
        toJson(routingContext.response().setStatusCode(201), object);
    }

    private void toJson(HttpServerResponse response, Object object) {
        response.putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(object));
    }

}
