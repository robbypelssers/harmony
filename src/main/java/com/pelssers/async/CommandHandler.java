package com.pelssers.async;


import io.vertx.core.Handler;

public class CommandHandler<S> {

    private final Command<S> command;
    private final Handler<S> handler;

    public CommandHandler(Command<S> command, Handler<S> handler) {
        this.command = command;
        this.handler = handler;
    }

    public static <S> CommandHandler<S> from(Command<S> command, Handler<S> handler) {
        return new CommandHandler<>(command, handler);
    }

    public void handle() {
        handler.handle(command.execute());
    }
}
