package com.pelssers.controller;


import com.pelssers.async.FailableCommand;
import com.pelssers.context.ApiMessages;
import com.pelssers.domain.UnprocessableEntity;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

public class GetPayloadCommand<S>  implements FailableCommand<S, UnprocessableEntity>, ApiMessages {

    private final RoutingContext routingContext;
    private final Class<S> clazz;

    public GetPayloadCommand(RoutingContext routingContext, Class<S> clazz) {
        this.routingContext = routingContext;
        this.clazz = clazz;
    }

    @Override
    public S execute() throws UnprocessableEntity {
        try {
            S payload = Json.decodeValue(routingContext.getBodyAsString(), clazz);
            return payload;
        } catch(DecodeException e) {
            throw new UnprocessableEntity(String.format(UNPROCESSABLE_ENTITY, routingContext.getBodyAsString()));
        }
    }
}
