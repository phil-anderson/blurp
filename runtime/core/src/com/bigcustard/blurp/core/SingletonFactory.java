package com.bigcustard.blurp.core;

import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.ui.*;

/**
 * This is where all the singletons for the model and runtime get instantiated, before being passed on to the model and
 * runtime singletons stores. It's done in a separate class to give tests the opportunity to use something else.
 */
public class SingletonFactory {

    private static volatile boolean initialised;

    // If adding stuff here, be sure to handle disposal as well as instantiation.
    private final ModelRepository modelRepository;
    private final RuntimeRepository runtimeRepository;
    private final BlurpScreen blurpScreen;
    private final Blurpifier blurpifier;
    private final Blurp blurp;
    private final Canvas canvas;
    private final IKeyboard keyboard;
    private final Utils utils;

    public SingletonFactory(Viewport viewport) {

        int width = (int) viewport.getWorldWidth();
        int height = (int) viewport.getWorldHeight();

        // Initialise runtime singletons
        modelRepository = makeModelRepository();
        runtimeRepository = makeRuntimeRepository();
        blurpScreen = makeBlurpScreen(viewport);
        blurpifier = makeBlurpifier();

        // Initialise model objects (i.e. ones that get passed into the running script)
        blurp = makeBlurpMethods();
        canvas = makeCanvas(width, height);
        keyboard = makeKeyboard();
        utils = makeUtils();
    }

    private Utils makeUtils() {

        return new Utils();
    }

    public void initialiseSingletons() {

        MSS.setInstances(modelRepository, canvas);
        RSS.setInstances(runtimeRepository, blurpScreen, blurpifier, blurp, canvas, keyboard, utils);
    }

    protected BlurpActions makeBlurpMethods() {

        return new BlurpActions();
    }

    protected  Keyboard makeKeyboard() {

        return new Keyboard();
    }

    protected  Canvas makeCanvas(int width, int height) {

        return new Canvas(width, height);
    }

    protected  Blurpifier makeBlurpifier() {

        return new Blurpifier();
    }

    protected  BlurpScreen makeBlurpScreen(Viewport viewport) {

        return new BlurpScreen(viewport);
    }

    protected  RuntimeRepository makeRuntimeRepository() {

        return new RuntimeRepository(modelRepository);
    }

    protected  ModelRepository makeModelRepository() {

        return new ModelRepository();
    }
}
