package com.bigcustard.blurp.core;

import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.ui.*;

/**
 * SF stands for SingletonFactory. It's a single, big-bad place for instantiating and storing singletons.
 * <p>
 * The shortened name is an attempt to cause minimal visual impact on calling code.
 */
public class SF {

    private static volatile boolean initialised;

    // If adding stuff here, be sure to handle disposal as well as instantiation.
    private static Blurpifier blurpifier;
    private static ModelRepositoryWrapper modelRepositoryWrapper;
    private static RuntimeRepository runtimeRepository;
    private static BlurpScreen blurpScreen;

    private SF() { }

    // TODO: Make this take some sort of BlurpConfig object instead.
    public synchronized static void instantiateSingletons(Viewport viewport) {

        if(initialised) throw new IllegalStateException("Sungletons can only be instantiated once.");

        blurpifier = new Blurpifier();
        modelRepositoryWrapper = new ModelRepositoryWrapper(blurpifier);
        runtimeRepository = new RuntimeRepository(modelRepositoryWrapper);
        blurpScreen = new BlurpScreen(viewport);
    }

    public synchronized static void dispose() {

        modelRepositoryWrapper.dispose();
        runtimeRepository.dispose();
        blurpScreen.dispose();

        modelRepositoryWrapper = null;
        runtimeRepository = null;
        blurpScreen = null;
        blurpifier = null;

        initialised = false;
    }

    // Note - There's no getter for ModelRepository as it shouldn't be exposed.

    public static RuntimeRepository getRuntimeRepository() {

        return runtimeRepository;
    }

    public static Blurpifier getBlurpifier() {

        return blurpifier;
    }

    public static BlurpScreen getBlurpScreen() {

        return blurpScreen;
    }
}
