package com.bigcustard.blurp.core;

import static com.bigcustard.blurp.core.Blurpifier.State.*;

/**
 * Handles synchronization between model and runtime during a blurpify, and handles blocking until it's done.
 */
public class Blurpifier implements IBlurpifier {

    // No need for an Active state at present, but I'm open to the suggestion.
    public static enum State {
        Dormant, Requested, Complete
    }

    private volatile State state = Dormant;

    @Override
    public synchronized void blurpify() {

        if(state != Dormant) throw new IllegalStateException("Already blurpifying"); // Shouldn't be possible.

        state = Requested;
        while(state != Complete) {
            try {
                wait();
            } catch(InterruptedException e) { } // Do nothing, never going to happen.
        }
        state = Dormant;
    }

    public synchronized State getState() {

        return state;
    }

    public synchronized void setState(State state) {

        this.state = state;
        notifyAll();
    }
}
