package com.pelssers.domain;


public class UnprocessableEntity extends HarmonyException {

    public UnprocessableEntity(String message) {
        super(message);
    }
}
