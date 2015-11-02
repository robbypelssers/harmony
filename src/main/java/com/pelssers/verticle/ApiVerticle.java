package com.pelssers.verticle;

import com.pelssers.context.HarmonyProperties;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;


public class ApiVerticle extends AbstractVerticle implements HarmonyProperties{

    private final Router router;
    private final Environment environment;

    public ApiVerticle(ApplicationContext context) {
        router = context.getBean(Router.class);
        environment = context.getBean(Environment.class);
    }

    @Override
    public void start(Future<Void> fut) {
        //@formatter:off
        vertx
            .createHttpServer()
            .requestHandler(router::accept)
            .listen(
                //retrieve port number from config or default to port 8080
                environment.getProperty(HTTP_PORT, Integer.class, 8080),
                result -> {
                    if (result.succeeded()) {
                        fut.complete();
                    } else {
                        fut.fail(result.cause());
                    }
            });
        //@formatter:on
    }
}
