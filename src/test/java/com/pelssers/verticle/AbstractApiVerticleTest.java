package com.pelssers.verticle;

import com.pelssers.context.HarmonyProperties;
import com.pelssers.context.HarmonyTestConfiguration;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static com.pelssers.fixtures.UserApiFixtures.newUser;

@RunWith(VertxUnitRunner.class)
public abstract class AbstractApiVerticleTest {

    protected ApplicationContext applicationContext;
    protected Vertx vertx;
    protected Integer port;
    protected String host;

    @Before
    public void setUp(TestContext context) {
        applicationContext = new AnnotationConfigApplicationContext(HarmonyTestConfiguration.class);
        port = applicationContext.getEnvironment().getProperty(HarmonyProperties.HTTP_PORT, Integer.class);
        host = applicationContext.getEnvironment().getProperty(HarmonyProperties.HOST);
        vertx = applicationContext.getBean(Vertx.class);
        vertx.deployVerticle(new ApiVerticle(applicationContext), context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    public Handler<HttpClientResponse> responseLogHandler() {
        return response -> {
            System.out.println("****************************************************");
            System.out.println("StatusCode: " + response.statusCode());
            System.out.println("Body:");
            response.bodyHandler(body -> {
                System.out.println(body);
                System.out.println("****************************************************");
            });
        };
    }


    public void post(TestContext context, String uri, Object payload, Handler<HttpClientResponse>... handlers ) {
        final Async async = context.async();
        String json = Json.encodePrettily(payload);
        String length = Integer.toString(json.length());

        //@formatter:off
        vertx.createHttpClient()
                .post(port, host, uri)
                .putHeader("content-type", "application/json")
                .putHeader("content-length", length)
                .handler(response -> {
                    for (Handler<HttpClientResponse> handler : handlers) {
                        handler.handle(response);
                    }
                    async.complete();
                })
                .write(json)
                .end();
        //@formatter:on
    }



}
