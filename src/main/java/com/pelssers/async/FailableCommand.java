package com.pelssers.async;


import io.vertx.core.Handler;

public interface FailableCommand<S,T extends Throwable> {

    S execute() throws T;

    default void handle(Handler<S> successHandler, Handler<T> errorHandler) {
        new FailableCommandHandler<S,T>(this).handle(successHandler, errorHandler);
    }
}
