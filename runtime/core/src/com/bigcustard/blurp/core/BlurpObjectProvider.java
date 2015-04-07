package com.bigcustard.blurp.core;

import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.ui.*;

/**
 * This is where all the singletons for the model and runtime get instantiated, before being passed on to the model and
 * runtime singletons stores. It's done in a separate class to give tests the opportunity to use something else.
 */
public class BlurpObjectProvider {

    private static volatile boolean initialised;

    // If adding stuff here, be sure to handle disposal as well as instantiation.
    private final ApiModelRepository modelRepository;
    private final RuntimeRepository runtimeRepository;
    private final BlurpScreen blurpScreen;
    private final Blurpifier blurpifier;
    private final Blurp blurp;
    private final Screen modelScreen;
    private final ModelScreenRenderer modelScreenRenderer;
    private final Keyboard keyboard;
    private final Utils utils;

    public BlurpObjectProvider(Viewport viewport) {

        modelRepository = new ApiModelRepository();
        blurpifier = new Blurpifier();
        keyboard = new KeyboardImpl();
        utils = new Utils();

        modelScreen = new Screen(viewport.getWorldWidth(), viewport.getWorldHeight());
        runtimeRepository = new RuntimeRepository(this);
        modelScreenRenderer = new ModelScreenRenderer(modelScreen);
        blurpScreen = new BlurpScreen(viewport, blurpifier, runtimeRepository, new ModelScreenRenderer(modelScreen));
        blurp = new BlurpImpl(modelRepository, modelScreen, blurpifier);
    }

    public ApiModelRepository getModelRepository() {

        return modelRepository;
    }

    public RuntimeRepository getRuntimeRepository() {

        return runtimeRepository;
    }

    public BlurpScreen getBlurpScreen() {

        return blurpScreen;
    }

    public Blurpifier getBlurpifier() {

        return blurpifier;
    }

    public Blurp getBlurp() {

        return blurp;
    }

    public Screen getModelScreen() {

        return modelScreen;
    }

    public Keyboard getKeyboard() {

        return keyboard;
    }

    public Utils getUtils() {

        return utils;
    }
}
