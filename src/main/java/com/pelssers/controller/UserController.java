package com.pelssers.controller;

import com.pelssers.async.CommandHandler;
import com.pelssers.async.FailableCommandHandler;
import com.pelssers.context.ApiParameters;
import com.pelssers.domain.rest.User;
import com.pelssers.service.users.UserService;
import io.vertx.ext.web.RoutingContext;

public class UserController extends AbstractController implements ApiParameters {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void findAll(RoutingContext routingContext) {
        CommandHandler.from(userService.getUsers()).handle(users -> get(routingContext, users));
    }

    public void findOne(RoutingContext routingContext) {
        String email = getParameter(routingContext, EMAIL);
        FailableCommandHandler.from(userService.findUser(email)).handle(
                user -> get(routingContext, user),
                resourceNotFound -> notFound(routingContext, resourceNotFound));
    }

    public void update(RoutingContext routingContext) {
        FailableCommandHandler.from(getPayloadCommand(routingContext, User.class)).handle(
                user ->
                    FailableCommandHandler.from(userService.updateUser(user)).handle(
                            x -> noContent(routingContext),
                            resourceNotFound -> notFound(routingContext, resourceNotFound)),
                unprocessableEntity -> unprocessable(routingContext, unprocessableEntity)
        );
    }

    public void create(RoutingContext routingContext) {
        FailableCommandHandler.from(getPayloadCommand(routingContext, User.class)).handle(
              user ->
                  FailableCommandHandler.from(userService.createUser(user)).handle(
                          newUser -> create(routingContext, newUser),
                          conflict -> conflict(routingContext, conflict)),
              unprocessableEntity -> unprocessable(routingContext, unprocessableEntity)
        );
    }


}
