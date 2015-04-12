package com.bigcustard.blurp.core;

import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.ui.*;

/**
 * This is where all the singletons for the model and runtime get instantiated, before being passed on to the model and
 * runtime singletons stores. It's done in a separate class to give tests the opportunity to use something else.
 */
public class BlurpObjectProvider {

    private final BlurpConfiguration blurpConfiguration;
    private final ModelRepository modelRepository;
    private final Blurpifier blurpifier;
    private final Keyboard keyboard;
    private final Utils utils;
    private final Keys keys;
    private final Screen modelScreen;
    private final RuntimeRepository runtimeRepository;
    private final BlurpScreen blurpScreen;
    private final Blurp blurp;

    public BlurpObjectProvider(BlurpConfiguration blurpConfiguration) {

        this.blurpConfiguration = blurpConfiguration;

        modelRepository = new ModelRepository();
        blurpifier = new Blurpifier();
        keyboard = new KeyboardImpl();
        utils = new Utils();
        keys = new Keys();

        Viewport viewport = blurpConfiguration.getViewport();
        modelScreen = new Screen(viewport.getWorldWidth(), viewport.getWorldHeight());
        runtimeRepository = new RuntimeRepository(this);

        ModelScreenRenderer modelScreenRenderer = new ModelScreenRenderer(modelScreen);
        blurpScreen = new BlurpScreen(viewport, blurpifier, runtimeRepository, modelScreenRenderer);
        blurp = new BlurpImpl(modelRepository, modelScreen, blurpifier);
    }

    public BlurpConfiguration getBlurpConfiguration() {

        return blurpConfiguration;
    }

    public ModelRepository getModelRepository() {

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

    public Keys getKeys() {

        return keys;
    }
}
