package com.pelssers.context;

import com.pelssers.controller.UserController;
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
        UserController userController = controllerConfig.userController();
        userApiRouter.get("/").handler(userController::findAll);
        userApiRouter.get("/:email").handler(userController::findOne);
        userApiRouter.route("/*").handler(BodyHandler.create());
        userApiRouter.post("/").handler(userController::create);
        userApiRouter.put("/").handler(userController::update);
        return userApiRouter;
    }

}
