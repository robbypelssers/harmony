package com.pelssers.controller;

import com.pelssers.async.CommandHandler;
import com.pelssers.async.FailableCommandHandler;
import com.pelssers.context.ApiParameters;
import com.pelssers.domain.rest.User;
import com.pelssers.service.users.UserService;
import io.vertx.ext.web.RoutingContext;

public class UserApiController extends AbstractApiController implements ApiParameters {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    public void findAll(RoutingContext routingContext) {
        CommandHandler.from(userService.getUsers(), users -> get(routingContext, users)).handle();
    }

    public void findOne(RoutingContext routingContext) {
        String email = getParameter(routingContext, EMAIL);
        FailableCommandHandler.from(
                userService.findUser(email),
                user -> get(routingContext, user),
                resourceNotFound -> notFound(routingContext, resourceNotFound)
        ).handle();
    }

    public void updateUser(RoutingContext routingContext) {
        getPayloadHandler(routingContext, User.class,
                user ->  FailableCommandHandler.from(
                            userService.updateUser(user),
                            x -> update(routingContext),
                            resourceNotFound -> notFound(routingContext, resourceNotFound)).handle()
        ).handle();
    }

    public void createUser(RoutingContext routingContext) {
        getPayloadHandler(routingContext, User.class,
                user ->  FailableCommandHandler.from(
                            userService.createUser(user),
                            newUser -> create(routingContext, newUser),
                            conflict -> conflict(routingContext, conflict)).handle()
        ).handle();
    }


}
