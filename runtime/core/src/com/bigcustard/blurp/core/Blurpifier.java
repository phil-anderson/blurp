package com.bigcustard.blurp.core;

import static com.bigcustard.blurp.core.Blurpifier.BlurpifyRequestState.*;

/**
 * Handles synchronization between model and runtime during a blurpify, and handles blocking until it's done.
 */
public class Blurpifier {

    public static enum BlurpifyRequestState { Dormant, Requested }
    public static enum BlurpifyRenderState { NoRequest, RequestAcknowledged, RequestComplete }

    private volatile BlurpifyRequestState requestState = Dormant;
    private volatile BlurpifyRenderState renderState = BlurpifyRenderState.NoRequest;

    private volatile RuntimeException exception;

    public void blurpify() {

        synchronized(this) {
            if(requestState != Dormant) throw new IllegalStateException("Already blurpifying"); // Shouldn't be possible.

            requestState = Requested;
            waitForRequestAcknowledge();
            waitForRequestCompleteState();

            try {
                if(exception != null) {
                    throw exception;
                }
            } finally {
                renderState = BlurpifyRenderState.NoRequest;
                requestState = Dormant;
            }
        }

        while(BlurpState.paused) {
            try { Thread.sleep(1); } catch(InterruptedException e) { }
        }
    }

    private void waitForRequestAcknowledge() {

        // If render is very fast, then may already be complete, so check for both.
        while(renderState != BlurpifyRenderState.RequestComplete && renderState != BlurpifyRenderState.RequestComplete) {
            try {
                wait();
            } catch(InterruptedException e) { } // Do nothing, never going to happen.
        }
    }

    private void waitForRequestCompleteState() {

        while(renderState != BlurpifyRenderState.RequestComplete) {
            try {
                wait();
            } catch(InterruptedException e) { } // Do nothing, never going to happen.
        }
    }

    public synchronized BlurpifyRequestState getRequestState() {

        return requestState;
    }

    public void setRenderState(BlurpifyRenderState blurpifyRenderState) {

        this.renderState = blurpifyRenderState;
        notifyAll();
    }

    public synchronized void setException(RuntimeException exception) {

        this.exception = exception;
    }

    public void reset() {

        this.requestState = Dormant;
        this.renderState = BlurpifyRenderState.NoRequest;
        this.exception = null;
    }
}
