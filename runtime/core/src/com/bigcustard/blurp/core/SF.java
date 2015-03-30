package com.bigcustard.blurp.core;

import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.ui.*;

/**
 * SF stands for Singleton Factory. It's a single, big-bad place for instantiating and storing singletons that the
 * runtime requires. There's also a {@link MSS MSS (Model Repository Store) that serves a similar purpose for the
 * model to hold its repository, except that it's instantiated in here and passed on.
 * <p>
 * The shortened name is an attempt to cause minimal visual impact on calling code.
 */
public class SF {

    private static volatile boolean initialised;

    // If adding stuff here, be sure to handle disposal as well as instantiation.
    private static ModelRepository modelRepository;
    private static RuntimeRepository runtimeRepository;
    private static BlurpScreen blurpScreen;
    private static Blurpifier blurpifier;
    private static Canvas canvas;
    private static IKeyboard keyboard;
    private static Blurp blurp;

    private SF() { }

    // TODO: Make this take some sort of BlurpConfig object instead.
    public synchronized static void instantiateSingletons(Viewport viewport) {

        if(initialised) throw new IllegalStateException("Singletons can only be instantiated once.");

        int width = (int) viewport.getWorldWidth();
        int height = (int) viewport.getWorldHeight();

        // Initialise runtime singletons
        modelRepository = new ModelRepository();
        runtimeRepository = new RuntimeRepository(modelRepository);
        blurpScreen = new BlurpScreen(viewport);
        blurpifier = new Blurpifier();

        // Initialise model objects (i.e. ones that get passed into the running script)
        canvas = new Canvas(width, height);
        keyboard = new Keyboard();
        blurp = new BlurpMethods();

        // Inject the model repository into the model API
        MSS.setInstances(modelRepository, canvas);
    }

    public synchronized static void dispose() {

        modelRepository.dispose();
        runtimeRepository.dispose();
        blurpScreen.dispose();
        MSS.dispose();

        modelRepository = null;
        runtimeRepository = null;
        blurpScreen = null;
        blurpifier = null;
        canvas = null;
        keyboard = null;

        initialised = false;
    }

    public static ModelRepository getModelRepository() {

        return modelRepository;
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
