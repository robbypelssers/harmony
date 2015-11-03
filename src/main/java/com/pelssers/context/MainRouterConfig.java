package com.pelssers.context;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainRouterConfig {

    @Autowired
    VertxConfig vertxConfig;

    @Autowired
    private ApiRouterConfig apiRouterConfig;

    @Bean
    public Router mainRouter() {
        Router mainRouter = Router.router(vertxConfig.vertx());

        mainRouter.mountSubRouter("/api", apiRouterConfig.apiRouter());

        // Serve static resources from the /assets directory
        mainRouter.route("/assets/*").handler(StaticHandler.create("assets"));
        return mainRouter;
    }




}
