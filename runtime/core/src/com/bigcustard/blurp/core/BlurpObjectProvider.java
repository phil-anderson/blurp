package com.bigcustard.blurp.core;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.core.effects.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.Screen;
import com.bigcustard.blurp.model.Sprite;
import com.bigcustard.blurp.model.effects.*;
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
    private final Effects effects;
    private final TweenManager tweener;
    private final BlurpScreen blurpScreen;

    private final Blurp blurp;
    private FontHolder systemFont;

    public BlurpObjectProvider(BlurpConfiguration blurpConfiguration) {

        this.blurpConfiguration = blurpConfiguration;

        this.modelRepository = new ModelRepository();
        this.keyboard = new KeyboardImpl();
        this.utils = new Utils();

        Viewport viewport = blurpConfiguration.getViewport();
        float width = viewport.getWorldWidth();
        float height = viewport.getWorldHeight();
        this.modelScreen = new ScreenImpl(width, height);

        this.effects = new EffectsImpl();
        this.tweener = initTweener();

        this.runtimeScreen = new RuntimeScreen(modelScreen);
        RuntimeScreenRenderer runtimeScreenRenderer = new RuntimeScreenRenderer(runtimeScreen);
        this.blurpifier = new Blurpifier();
        this.runtimeRepository = new RuntimeRepository(this);
        this.blurp = new BlurpImpl(runtimeRepository, modelRepository, modelScreen, blurpifier);
        this.blurpScreen = new BlurpScreen(viewport, runtimeRepository, runtimeScreenRenderer, blurpifier, this, tweener);
    }

    private TweenManager initTweener() {

        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        return new TweenManager();
    }

    // Stuff that would normally go in the application.create() method, but can;t because we may be hosted in a
    // pre-existing libGdx app (i.e. PlanetBlurp).
    public void onLibGdxInitialised() {

        Texture systemFontTexture = new Texture(Gdx.files.classpath("RobotoCondensed.png"), true);
        systemFontTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        BitmapFont font = new BitmapFont(Gdx.files.classpath("RobotoCondensed.fnt"),
                                         new TextureRegion(systemFontTexture), false);
        systemFont = new FontHolder(font);
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

    public TweenManager getTweener() {

        return tweener;
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

    public FontHolder getSystemFont() {

        return systemFont;
    }

    public Effects getEffects() {

        return effects;
    }
}
