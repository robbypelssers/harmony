package com.pelssers.verticle;

import com.pelssers.HarmonyExceptionMessage;
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

@RunWith(VertxUnitRunner.class)
public abstract class AbstractVerticleTest {

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
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
        ((ConfigurableApplicationContext)applicationContext).close();
    }

    public Handler<HttpClientResponse> emptyHandler() {
        return response -> {};
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

    public void get(TestContext context, String uri, Handler<HttpClientResponse>... handlers ) {
        final Async async = context.async();
        vertx.createHttpClient().getNow(port, host, uri, response -> {
            for (Handler<HttpClientResponse> handler : handlers) {
                handler.handle(response);
                async.complete();
            }
        });
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

    public void assertStatus(TestContext context, HttpClientResponse response, int status) {
        context.assertEquals(response.statusCode(), status);
    }

    public void assertContentTypeIsJson(TestContext context, HttpClientResponse response) {
        context.assertTrue(response.headers().get("content-type").contains("application/json"));
    }

    public void assertException(TestContext context, HttpClientResponse response, String message, String type) {
        response.bodyHandler(body -> {
            HarmonyExceptionMessage exception = Json.decodeValue(body.toString(), HarmonyExceptionMessage.class);
            context.assertEquals(message, exception.getMessage());
            context.assertEquals(type, exception.getType());
        });
    }



}
