package com.pelssers.async;


import io.vertx.core.Handler;

public class CommandHandler<S> {

    private Command<S> command;

    public CommandHandler(Command<S> command) {
        this.command = command;
    }

    public static <S> CommandHandler<S> from(Command<S> command) {
        return new CommandHandler<>(command);
    }

    public void handle(Handler<S> handler) {
        handler.handle(command.execute());
    }
}
