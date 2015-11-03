package com.pelssers.fixtures;


import com.pelssers.domain.rest.User;
import java.time.LocalDate;
import java.time.Month;

public class UserApiFixtures {

    public static User userRobby() {
        return new User()
                .withBirthDay(LocalDate.of(1977, Month.FEBRUARY, 7))
                .withEmail("robby.pelssers@gmail.com")
                .withFirstName("Robby")
                .withLastName("Pelssers");
    }

    public static User userDavy() {
        return new User()
                .withBirthDay(LocalDate.of(1977, Month.FEBRUARY, 7))
                .withEmail("davy.pelssers@gmail.com")
                .withFirstName("Davy")
                .withLastName("Pelssers");
    }

    public static User userLindsey() {
        return new User()
                .withBirthDay(LocalDate.of(2002, Month.DECEMBER, 23))
                .withEmail("lindsey.pelssers@gmail.com")
                .withFirstName("Lindsey")
                .withLastName("Pelssers");
    }

}
