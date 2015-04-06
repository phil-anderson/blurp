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
    private final Colours colours;

    public BlurpObjectProvider(Viewport viewport) {

        int width = (int) viewport.getWorldWidth();
        int height = (int) viewport.getWorldHeight();

        modelRepository = makeModelRepository();
        runtimeRepository = makeRuntimeRepository();
        blurpifier = makeBlurpifier();
        modelScreen = makeModelScreen(width, height);
        modelScreenRenderer = makeModelScreenRenderer();
        blurpScreen = makeBlurpScreen(viewport, blurpifier, runtimeRepository, modelScreen);
        keyboard = makeKeyboard();
        utils = makeUtils();
        blurp = makeBlurpImpl(modelRepository, modelScreen, blurpifier);
        colours = Colours.INSTANCE;
    }

    protected ModelScreenRenderer makeModelScreenRenderer() {

        return new ModelScreenRenderer(modelScreen);
    }

    private Utils makeUtils() {

        return new Utils();
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

    // Separate protected factory methods to make it easier to mock for tests
    protected BlurpImpl makeBlurpImpl(ApiModelRepository apiModelRepository, Screen screen, Blurpifier blurpifier) {

        return new BlurpImpl(apiModelRepository, screen, blurpifier);
    }

    protected KeyboardImpl makeKeyboard() {

        return new KeyboardImpl();
    }

    protected Screen makeModelScreen(int width, int height) {

        return new Screen(width, height);
    }

    protected  Blurpifier makeBlurpifier() {

        return new Blurpifier();
    }

    protected  BlurpScreen makeBlurpScreen(Viewport viewport, Blurpifier blurpifier, RuntimeRepository runtimeRepository, Screen screen) {

        return new BlurpScreen(viewport, blurpifier, runtimeRepository, new ModelScreenRenderer(screen));
    }

    protected  RuntimeRepository makeRuntimeRepository() {

        return new RuntimeRepository(this);
    }

    protected ApiModelRepository makeModelRepository() {

        return new ApiModelRepository();
    }

    public Colours getColours() {

        return colours;
    }
}
