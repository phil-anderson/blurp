package com.bigcustard.blurp.core;

/**
 * Allows the user to call methods that specific to the Blurp engine.
 */
public class BlurpMethods extends Blurp {

    BlurpMethods() { }

    public void blurpify() {

        SF.getBlurpifier().blurpify();
    }
}
