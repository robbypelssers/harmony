package com.pelssers.domain;


import com.pelssers.HarmonyException;

public class UserAlreadyExistsException extends HarmonyException {

    public UserAlreadyExistsException(String email) {
        super(String.format("User already exists with email '%s'.", email));
    }
}
