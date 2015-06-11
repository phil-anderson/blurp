package com.bigcustard.blurp.core;

public class BlurpTerminatedException extends RuntimeException {

    public static enum CompletionAction { Normal, Terminate, Restart }

    private final CompletionAction action;

    public BlurpTerminatedException(CompletionAction action) {

        this.action = action;
    }

    public CompletionAction getAction() {

        return action;
    }
}
