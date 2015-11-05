package com.pelssers.async;

import io.vertx.core.Handler;


public class FailableCommandHandler<S,T extends Throwable> {

    private FailableCommand<S,T> failableCommand;
    private final Handler<S> successHandler;
    private final Handler<T> errorHandler;


    public FailableCommandHandler(FailableCommand<S,T> failableCommand, Handler<S> successHandler, Handler<T> errorHandler) {
        this.failableCommand = failableCommand;
        this.successHandler = successHandler;
        this.errorHandler = errorHandler;
    }

    public static <S,T extends Throwable> FailableCommandHandler<S,T> from(
            FailableCommand<S,T> failableCommand, Handler<S> successHandler, Handler<T> errorHandler) {
        return new FailableCommandHandler<>(failableCommand, successHandler, errorHandler);
    }

    public void handle() {
        try {
            successHandler.handle(failableCommand.execute());
        } catch (Throwable e) {
            errorHandler.handle((T) e);
        }
    }
}
