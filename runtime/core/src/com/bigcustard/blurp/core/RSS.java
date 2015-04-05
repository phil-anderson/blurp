package com.bigcustard.blurp.core;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.ui.*;

/**
 * RSS stands for Runtime Singleton Store. It's a single, big-bad place for storing singletons that the runtime
 * requires. There's also a {@link MSS MSS (Model Singleton Store) that serves a similar purpose for the model.
 * It's not nice, but it is expedient.
 * <p>
 * The shortened name is an attempt to cause minimal visual impact on calling code.
 */
public class RSS {

    private static volatile boolean initialised;

    // If adding stuff here, be sure to handle disposal as well as instantiation.
    private static RuntimeRepository runtimeRepository;
    private static BlurpScreen blurpScreen;
    private static Blurpifier blurpifier;
    private static Blurp blurp;
    private static Canvas canvas;
    private static Keyboard keyboard;
    private static Utils utils;

    private RSS() { }

    public synchronized static void setInstances(RuntimeRepository runtimeRepository,
                                                 BlurpScreen blurpScreen,
                                                 Blurpifier blurpifier,
                                                 Blurp blurp,
                                                 Canvas canvas,
                                                 Keyboard keyboard,
                                                 Utils utils) {

        if(initialised) throw new IllegalStateException("Singletons can only be instantiated once.");

        RSS.runtimeRepository = runtimeRepository;
        RSS.blurpScreen = blurpScreen;
        RSS.blurpifier = blurpifier;
        RSS.blurp = blurp;
        RSS.canvas = canvas;
        RSS.keyboard = keyboard;
        RSS.utils = utils;
    }

    public synchronized static void dispose() {

        runtimeRepository.dispose();
        blurpScreen.dispose();
        MSS.dispose();

        runtimeRepository = null;
        blurpScreen = null;
        blurpifier = null;
        blurp = null;
        canvas = null;
        keyboard = null;
        utils = null;

        initialised = false;
    }

    public static RuntimeRepository getRuntimeRepository() {

        return runtimeRepository;
    }

    public static BlurpScreen getBlurpScreen() {

        return blurpScreen;
    }

    public static Blurpifier getBlurpifier() {

        return blurpifier;
    }

    public static Blurp getBlurp() {

        return blurp;
    }

    public static Canvas getCanvas() {

        return canvas;
    }

    public static Keyboard getKeyboard() {

        return keyboard;
    }

    public static Utils getUtils() {

        return utils;
    }
}
