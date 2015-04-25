package com.bigcustard.blurp.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.Screen;
import com.bigcustard.blurp.runtimemodel.*;
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
    private final Screen modelScreen;
    private final RuntimeScreen runtimeScreen;
    private final RuntimeRepository runtimeRepository;
    private final BlurpScreen blurpScreen;
    private final Blurp blurp;
    private BitmapFont systemFont;

    public BlurpObjectProvider(BlurpConfiguration blurpConfiguration) {

        this.blurpConfiguration = blurpConfiguration;

        modelRepository = new ModelRepository();
        keyboard = new KeyboardImpl();
        utils = new Utils();

        Viewport viewport = blurpConfiguration.getViewport();
        float width = viewport.getWorldWidth();
        float height = viewport.getWorldHeight();
        modelScreen = new ScreenImpl(width, height);

        runtimeScreen = new RuntimeScreen(modelScreen);
        RuntimeScreenRenderer runtimeScreenRenderer = new RuntimeScreenRenderer(runtimeScreen);
        blurpifier = new Blurpifier();
        runtimeRepository = new RuntimeRepository(this);
        blurp = new BlurpImpl(runtimeRepository, modelRepository, modelScreen, blurpifier);
        blurpScreen = new BlurpScreen(viewport, runtimeRepository, runtimeScreenRenderer, blurpifier, this);
    }

    // Stuff that would normally go in the application.create() method, but can;t because we may be hosted in a
    // pre-existing libGdx app (i.e. PlanetBlurp).
    public void onLibGdxInitialised() {

        Texture systemFontTexture = new Texture(Gdx.files.classpath("RobotoCondensed.png"), true);
        systemFontTexture.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.MipMapLinearLinear);
        systemFont = new BitmapFont(Gdx.files.classpath("RobotoCondensed.fnt"), new TextureRegion(systemFontTexture), false);
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

    public RuntimeScreen getRuntimeScreen() {

        return runtimeScreen;
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

    public BitmapFont getSystemFont() {

        return systemFont;
    }
}
