package com.pelssers.async;

import io.vertx.core.Handler;


public class FailableCommandHandler<S,T extends Throwable> {

    private FailableCommand<S,T> failableCommand;

    public FailableCommandHandler(FailableCommand<S,T> failableCommand) {
        this.failableCommand = failableCommand;
    }

    public void handle(Handler<S> successHandler, Handler<T> errorHandler) {
        try {
            successHandler.handle(failableCommand.execute());
        } catch (Throwable e) {
            errorHandler.handle((T) e);
        }
    }
}
