package com.pelssers.context;

import com.pelssers.controller.UserApiController;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiRouterConfig  {

    @Autowired
    VertxConfig vertxConfig;

    @Autowired
    private ControllerConfig controllerConfig;

    public Router apiRouter() {
        Router apiRouter = Router.router(vertxConfig.vertx());
        apiRouter.mountSubRouter("/users", userApiRouter());
        return apiRouter;
    }

    public Router userApiRouter() {
        Router userApiRouter = Router.router(vertxConfig.vertx());
        UserApiController userApiController = controllerConfig.userController();
        userApiRouter.get("/").handler(userApiController::findAll);
        userApiRouter.get("/:email").handler(userApiController::findOne);
        userApiRouter.route("/*").handler(BodyHandler.create());
        userApiRouter.post("/").handler(userApiController::createUser);
        userApiRouter.put("/").handler(userApiController::updateUser);
        return userApiRouter;
    }

}
