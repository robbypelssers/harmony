package com.pelssers;

public class HarmonyExceptionMessage {

    private String message;
    private String type;

    public HarmonyExceptionMessage() {

    }

    public HarmonyExceptionMessage(HarmonyException exception) {
        this.message = exception.getMessage();
        this.type = exception.getClass().getName();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return this.message;
    }

    public String getType() {
        return this.type;
    }
}
