package com.pelssers.domain;


public class HarmonyExceptionMessage {

    private String message;
    private String type;

    public HarmonyExceptionMessage() {

    }

    public HarmonyExceptionMessage(HarmonyException exception) {
        this.message = exception.getMessage();
        this.type = exception.getClass().getSimpleName();
    }

    public String getMessage() {
        return this.message;
    }

    public String getType() {
        return this.type;
    }
}
