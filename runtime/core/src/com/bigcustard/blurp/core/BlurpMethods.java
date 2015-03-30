package com.bigcustard.blurp.core;

/**
 * Behind the scenes, your blurp program extends this class, and will have access to the protected properties and
 * methods in it.
 * <p>
 * If nothing else, you should read the documentation on the {@link #blurpify()} method.
 * <p>
 * TEMPORARY - For now, your subclass should implement Runnable.run(). This will change once we establish a solid
 * scripting strategy.
 */
public class BlurpMethods extends Blurp {

//    BlurpMethods(Canvas canvas, IKeyboard keyboard) {
//
//        super(canvas, keyboard);
//    }

    BlurpMethods() { }

    public void blurpify() {

        SF.getBlurpifier().blurpify();
    }
}
