package com.bigcustard.blurp.core;

/**
 * Allows the user to call methods that specific to the Blurp engine.
 */
public class BlurpActions extends Blurp {

    BlurpActions() { }

    public void blurpify() {

        RSS.getBlurpifier().blurpify();
    }
}
