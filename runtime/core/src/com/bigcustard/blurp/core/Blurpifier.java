package com.bigcustard.blurp.core;

/**
 * Handles synchronization between model and runtime during a blurpify, and handles blocking until it's done.
 */
public class Blurpifier implements IBlurpifier {

    // No need for an Active state at present, but I'm open to the suggestion.
    public static enum State {
        Dormant, Requested, Complete
    }

    private volatile State state = State.Dormant;

    @Override
    public synchronized void blurpify() {

        if(state != State.Dormant) throw new IllegalStateException("Already blurpifying"); // Shouldn't be possible.

        state = State.Requested;
        while(state != State.Complete) {
            try {
                wait();
            } catch(InterruptedException e) { } // Do nothing, never going to happen.
        }
        state = State.Dormant;
    }

    public synchronized State getState() {

        return state;
    }

    public synchronized void setState(State state) {

        this.state = state;
        notifyAll();
    }
}
