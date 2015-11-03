package com.pelssers.controller;

import com.pelssers.context.ApiParameters;
import com.pelssers.domain.Conflict;
import com.pelssers.domain.ResourceNotFound;
import com.pelssers.domain.UnprocessableEntity;
import com.pelssers.domain.rest.User;
import com.pelssers.service.UserService;
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
        } catch (ResourceNotFound resourceNotFound) {
            notFound(routingContext, resourceNotFound);
        }
    }

    public void update(RoutingContext routingContext) {
        try {
            final User user = getPayload(routingContext, User.class);
            userService.update(user);
            noContent(routingContext);
        } catch (ResourceNotFound resourceNotFound) {
            notFound(routingContext, resourceNotFound);
        } catch (UnprocessableEntity unprocessableEntity) {
            unprocessable(routingContext, unprocessableEntity);
        }
    }

    public void create(RoutingContext routingContext) {
        try {
            final User user = getPayload(routingContext, User.class);
            User newUser = userService.createUser(user);
            create(routingContext, newUser);
        } catch (Conflict conflict)   {
            conflict(routingContext, conflict);
        } catch (UnprocessableEntity unprocessableEntity) {
            unprocessable(routingContext, unprocessableEntity);
        }
    }


}
