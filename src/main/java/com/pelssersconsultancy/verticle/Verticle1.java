package com.pelssersconsultancy.verticle;

import com.pelssersconsultancy.controller.UserController;
import com.pelssersconsultancy.repository.InMemoryUserRepository;
import com.pelssersconsultancy.repository.UserRepository;
import com.pelssersconsultancy.service.DefaultUserService;
import com.pelssersconsultancy.service.UserService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;


public class Verticle1 extends AbstractVerticle {

    private  UserController userController;

    @Override
    public void start(Future<Void> fut) {
        //@formatter:off

        //create controllers
        userController = userController();

        // Create a router object.
        Router router = createRouter();

        vertx
            .createHttpServer()
            .requestHandler(router::accept)
            .listen(
                //retrieve port number from config or default to port 8080
                config().getInteger("http.port", 8080),
                result -> {
                    if (result.succeeded()) {
                        fut.complete();
                    } else {
                        fut.fail(result.cause());
                    }
            });
        //@formatter:on
    }

    private Router createRouter() {
        Router router = Router.router(vertx);

        // Bind "/" to our hello message - so we are still compatible.
//        router.route("/").handler(routingContext -> {
//            HttpServerResponse response = routingContext.response();
//            response
//                    .putHeader("content-type", "text/html")
//                    .end("<h1>Hello from my first Vert.x 3 application</h1>");
//        });

        router.get("/api/users").handler(userController::findAll);
        router.route("/api/users*").handler(BodyHandler.create());
        router.post("/api/users").handler(userController::create);

        // Serve static resources from the /assets directory
        router.route("/assets/*").handler(StaticHandler.create("assets"));
        return router;
    }

    private UserController userController() {
        UserRepository userRepository = new InMemoryUserRepository();
        UserService userService = new DefaultUserService(userRepository);
        UserController controller = new UserController(userService);
        return controller;
    }
}
