package com.pelssers.verticle;


import static com.pelssers.fixtures.UserApiFixtures.*;

import com.pelssers.domain.rest.User;
import io.vertx.core.json.Json;
import io.vertx.ext.unit.TestContext;
import org.junit.Test;

public class UserApiTests extends AbstractApiVerticleTest {

    @Test
    public void createUser(TestContext context) {
        User newUser = newUser();
        post(context, "/api/users", newUser, response -> {
            context.assertEquals(response.statusCode(), 201);
            context.assertTrue(response.headers().get("content-type").contains("application/json"));
            response.bodyHandler(body -> {
                User userCreated = Json.decodeValue(body.toString(), User.class);
                context.assertNotNull(userCreated.getId());
                context.assertEquals("Robby", userCreated.getFirstName());
            });
        });
    }
}
