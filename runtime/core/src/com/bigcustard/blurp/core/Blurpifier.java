package com.bigcustard.blurp.core;

import static com.bigcustard.blurp.core.Blurpifier.State.*;

/**
 * Handles synchronization between model and runtime during a blurpify, and handles blocking until it's done.
 */
public class Blurpifier {

    // No need for an Active state at present, but I'm open to the suggestion.
    public static enum State {
        Dormant, Requested, Complete
    }

    private volatile State state = Dormant;
    private volatile Exception exception;

    public synchronized void blurpify() {

        if(state != Dormant) throw new IllegalStateException("Already blurpifying"); // Shouldn't be possible.

        state = Requested;
        while(state != Complete) {
            try {
                wait();
            } catch(InterruptedException e) { } // Do nothing, never going to happen.
        }
        state = Dormant;
        if(exception != null) {
            throw new BlurpException("Error blurpifying", exception);
        }
    }

    public synchronized State getState() {

        return state;
    }

    public void setException(Exception exception) {

        this.exception = exception;
    }

    public synchronized void setState(State state) {

        this.state = state;
        notifyAll();
    }
}
