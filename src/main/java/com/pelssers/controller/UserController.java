package com.pelssers.controller;

import com.pelssers.context.ApiParameters;
import com.pelssers.domain.Conflict;
import com.pelssers.domain.NotFound;
import com.pelssers.domain.rest.User;
import com.pelssers.service.UserService;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

public class UserController extends AbstractController implements ApiParameters {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void findAll(RoutingContext routingContext) {
        get(routingContext, userService.findAll());
    }

    public void findOne(RoutingContext routingContext) {
        String email = getParameter(routingContext, EMAIL);
        try {
            User user = userService.findOne(email);
            get(routingContext, user);
        } catch (NotFound e) {
            notFound(routingContext, e);
        }
    }

    public void create(RoutingContext routingContext) {
        final User user = Json.decodeValue(routingContext.getBodyAsString(), User.class);
        try {
            User newUser = userService.createUser(user);
            create(routingContext, newUser);
        } catch (Conflict e)   {
            conflict(routingContext, e);
        }
    }


}
