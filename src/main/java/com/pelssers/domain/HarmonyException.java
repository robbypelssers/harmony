package com.pelssers.domain;

public class HarmonyException extends Exception {

    public HarmonyException(String message) {
        super(message);
    }

    public HarmonyExceptionMessage liftException() {
        return new HarmonyExceptionMessage(this);
    }
}
