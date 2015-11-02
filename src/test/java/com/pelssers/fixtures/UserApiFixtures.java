package com.pelssers.fixtures;


import com.pelssers.domain.rest.User;
import io.vertx.core.json.Json;

import java.time.LocalDate;
import java.time.Month;

public class UserApiFixtures {

    public static User newUser() {
        return new User()
                .withBirthDay(LocalDate.of(1977, Month.FEBRUARY, 7))
                .withEmail("robby.pelssers@gmail.com")
                .withFirstName("Robby")
                .withLastName("Pelssers");
    }

}
