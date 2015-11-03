package com.pelssers.verticle;


import static com.pelssers.fixtures.UserApiFixtures.*;
import com.pelssers.domain.rest.User;
import io.vertx.core.json.Json;
import io.vertx.ext.unit.TestContext;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;


public class UserApiTests extends AbstractVerticleTest {

    @Before
    public void setUp(TestContext context) {
        super.setUp(context);
        vertx.deployVerticle(new ApiVerticle(applicationContext), context.asyncAssertSuccess());
        //insert two users Robby and Lindsey
        Arrays.asList(userRobby(), userLindsey()).forEach(user -> post(context, "/api/users", user, emptyHandler()));
    }

    @Test
    public void getUsers(TestContext context) {
       get(context, "/api/users",  response -> {
           assertStatus(context, response, 200);
           assertContentTypeIsJson(context, response);
           response.bodyHandler(body -> {
               User[] users = Json.decodeValue(body.toString(), User[].class);
               context.assertTrue(users.length == 2);
           });
       });
    }

    @Test
    public void createNonExistingUser(TestContext context) {
        post(context, "/api/users", userDavy(), response -> {
            assertStatus(context, response, 201);
            assertContentTypeIsJson(context, response);
            response.bodyHandler(body -> {
                User userCreated = Json.decodeValue(body.toString(), User.class);
                context.assertNotNull(userCreated.getId());
                context.assertEquals("Davy", userCreated.getFirstName());
            });
        });
    }

    @Test
    public void createExistingUser(TestContext context) {
        //try to insert user which already exists
        post(context, "/api/users", userRobby(), response2 -> {
            assertStatus(context, response2, 409);
            assertContentTypeIsJson(context, response2);
            assertException(context, response2, "User already exists with email 'robby.pelssers@gmail.com'.",
                    "com.pelssers.domain.UserAlreadyExistsException");
        });
    }


}
