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
    private static Canvas canvas;
    private static IKeyboard keyboard;
    private static Blurp blurp;

    private RSS() { }

    public synchronized static void setInstances(RuntimeRepository runtimeRepository,
                                                 BlurpScreen blurpScreen,
                                                 Blurpifier blurpifier,
                                                 Canvas canvas,
                                                 IKeyboard keyboard,
                                                 Blurp blurp ) {

        if(initialised) throw new IllegalStateException("Singletons can only be instantiated once.");

        RSS.runtimeRepository = runtimeRepository;
        RSS.blurpScreen = blurpScreen;
        RSS.blurpifier = blurpifier;
        RSS.canvas = canvas;
        RSS.keyboard = keyboard;
        RSS.blurp = blurp;
    }

    public synchronized static void dispose() {

        runtimeRepository.dispose();
        blurpScreen.dispose();
        MSS.dispose();

        runtimeRepository = null;
        blurpScreen = null;
        blurpifier = null;
        canvas = null;
        keyboard = null;

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

    public static Canvas getCanvas() {

        return canvas;
    }

    public static IKeyboard getKeyboard() {

        return keyboard;
    }

    public static Blurp getBlurp() {

        return blurp;
    }
}
