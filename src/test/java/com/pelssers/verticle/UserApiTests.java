package com.pelssers.verticle;


import static com.pelssers.fixtures.UserApiFixtures.*;

import com.pelssers.context.ApiMessages;
import com.pelssers.domain.rest.User;
import io.vertx.core.json.Json;
import io.vertx.ext.unit.TestContext;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;


public class UserApiTests extends AbstractVerticleTest implements ApiMessages {

    @Before
    public void setUp(TestContext context) throws IOException {
        super.setUp(context);

        //insert two users Robby and Lindsey
        Arrays.asList(userRobby(), userLindsey()).forEach(user -> post(context, "/api/users", user, emptyHandler()));
    }

    @Test
    public void getUsers(TestContext context) {
       get(context, "/api/users", response -> {
           assertStatus(context, response, 200);
           assertContentTypeIsJson(context, response);
           response.bodyHandler(body -> {
               User[] users = Json.decodeValue(body.toString(), User[].class);
               context.assertTrue(users.length == 2);
           });
       });
    }

    @Test
    public void findUser(TestContext context) {
        get(context, "/api/users/robby.pelssers@gmail.com", response -> {
            assertStatus(context, response, 200);
            assertContentTypeIsJson(context, response);
            response.bodyHandler(body -> {
                User user = Json.decodeValue(body.toString(), User.class);
                context.assertEquals("Robby", user.getFirstName());
                context.assertEquals("Pelssers", user.getLastName());
            });
        });
    }

    @Test
    public void findNonExistingUser(TestContext context) {
        final String email = "notexisting.pelssers@gmail.com";
        get(context, "/api/users/" + email, response -> {
            assertStatus(context, response, 404);
            assertContentTypeIsJson(context, response);
            assertException(context, response, String.format(USER_NOT_FOUND, email),
                    "ResourceNotFound");
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
        User userRobby = userRobby();
        //try to insert user which already exists
        post(context, "/api/users", userRobby, response2 -> {
            assertStatus(context, response2, 409);
            assertContentTypeIsJson(context, response2);
            assertException(context, response2, String.format(USER_ALREADY_EXISTS, userRobby.getEmail()),
                    "Conflict");
        });
    }

    @Test
    public void updateExistingUser(TestContext context) {
        final LocalDate birthDay = LocalDate.of(1977, Month.JANUARY, 10);
        User userRobby = userRobby();
        userRobby.setBirthDay(birthDay);
        put(context, "/api/users", userRobby, responseLogHandler());
    }


}
