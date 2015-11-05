package com.pelssers.async;


public interface Command<S> {

    S execute();

}
