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

    private volatile Exception exception;

    public synchronized void blurpify() {

        if(requestState != Dormant) throw new IllegalStateException("Already blurpifying"); // Shouldn't be possible.

        requestState = Requested;
        waitForRequestAcknowledge();
        waitForRequestCompleteState();
        renderState = BlurpifyRenderState.NoRequest;
        requestState = Dormant;

        if(exception != null) {
            throw new BlurpException("Error blurpifying", exception);
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

    public synchronized void setException(Exception exception) {

        this.exception = exception;
    }
}
