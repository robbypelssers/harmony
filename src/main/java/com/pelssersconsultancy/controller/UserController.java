package com.pelssersconsultancy.controller;

import com.pelssersconsultancy.domain.rest.User;
import com.pelssersconsultancy.service.UserService;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void findAll(RoutingContext routingContext) {
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(userService.findAll()));
    }

    public void create(RoutingContext routingContext) {
        final User user = Json.decodeValue(routingContext.getBodyAsString(), User.class);
        User newUser = userService.createUser(user);
        routingContext.response()
                .setStatusCode(201)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(newUser));

    }


}
