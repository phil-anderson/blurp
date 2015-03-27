package com.bigcustard.blurp.core;

public class BlurpException extends RuntimeException {

    public BlurpException(String message) {

        super(message);
    }

    public BlurpException(String message, Throwable cause) {

        super(message, cause);
    }
}
