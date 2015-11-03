package com.pelssers.context;

import com.pelssers.controller.UserController;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainRouterConfig implements ApiParameters {

    @Autowired
    VertxConfig vertxConfig;

    @Autowired
    private ControllerConfig controllerConfig;

    @Bean
    public Router mainRouter() {
        Router mainRouter = Router.router(vertxConfig.vertx());

        mainRouter.mountSubRouter("/api", apiRouter());

        // Serve static resources from the /assets directory
        mainRouter.route("/assets/*").handler(StaticHandler.create("assets"));
        return mainRouter;
    }


    public Router apiRouter() {
        Router router = Router.router(vertxConfig.vertx());
        UserController userController = controllerConfig.userController();
        router.get("/users").handler(userController::findAll);
        router.get("/users/:" + EMAIL).handler(userController::findOne);
        router.route("/users*").handler(BodyHandler.create());
        router.post("/users").handler(userController::create);
        return router;
    }


}
