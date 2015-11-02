package com.pelssers.context;

import com.pelssers.controller.UserController;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

    @Autowired
    private ControllerConfig controllerConfig;

    @Bean
    public Router router() {
        Router router = Router.router(vertx());
        UserController userController = controllerConfig.userController();
        router.get("/api/users").handler(userController::findAll);
        router.get("/api/users/:email").handler(userController::findOne);
        router.route("/api/users*").handler(BodyHandler.create());
        router.post("/api/users").handler(userController::create);

        // Serve static resources from the /assets directory
        router.route("/assets/*").handler(StaticHandler.create("assets"));
        return router;
    }

    @Bean
    public Vertx vertx() {
        return Vertx.vertx();
    }
}
