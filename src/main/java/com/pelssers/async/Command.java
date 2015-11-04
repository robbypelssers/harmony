package com.pelssers.async;


import io.vertx.core.Handler;

public interface Command<S> {

    S execute();

    default void handle(Handler<S> handler) {
        new CommandHandler<S>(this).handle(handler);
    }
}
