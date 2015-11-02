package com.pelssers.controller;

import com.pelssers.domain.rest.User;
import com.pelssers.service.UserService;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

public class UserController extends AbstractController {

    public static final String PARAM_EMAIL = "email";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void findAll(RoutingContext routingContext) {
        get(routingContext, userService.findAll());
    }

    public void findOne(RoutingContext routingContext) {
        String email = routingContext.request().getParam(PARAM_EMAIL);
        get(routingContext, userService.findOne(email));
    }

    public void create(RoutingContext routingContext) {
        final User user = Json.decodeValue(routingContext.getBodyAsString(), User.class);
        User newUser = userService.createUser(user);
        create(routingContext, newUser);
    }


}
