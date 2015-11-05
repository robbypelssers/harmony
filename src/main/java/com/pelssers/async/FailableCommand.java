package com.pelssers.async;

public interface FailableCommand<S,T extends Throwable> {

    S execute() throws T;

}
