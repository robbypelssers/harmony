package com.pelssers.verticle;


import static com.pelssers.fixtures.UserApiFixtures.*;

import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.junit.Test;

public class UserApiTests extends AbstractApiVerticleTest {

    @Test
    public void createUser(TestContext context) {
        final Async async = context.async();

        String json = newUser();
        String length = Integer.toString(json.length());


        //@formatter:off
        vertx.createHttpClient()
                .post(port, host, "/api/user")
                .putHeader("content-type", "application/json")
                .putHeader("content-length", length)
                .handler(response -> {
                    context.assertEquals(response.statusCode(), 201);
                    context.assertTrue(response.headers().get("content-type").contains("application/json"));
                })
                .write(json)
                .end();
        //@formatter:on
    }
}
