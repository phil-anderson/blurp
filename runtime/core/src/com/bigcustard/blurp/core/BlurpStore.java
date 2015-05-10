package com.bigcustard.blurp.core;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.core.effects.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.Camera;
import com.bigcustard.blurp.model.Screen;
import com.bigcustard.blurp.model.Sprite;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.ui.*;

/**
 * This is where all the singletons for the model and runtime get instantiated, before being passed on to the model and
 * runtime singletons stores. It's done in a separate class to give tests the opportunity to use something else.
 */
public class BlurpStore {

    public static BlurpConfiguration configuration;
    public static ModelRepository modelRepository;
    public static Screen modelScreen;
    public static Camera modelCamera;
    public static TweenManager tweener;
    public static Blurpifier blurpifier;
    public static RuntimeRepository runtimeRepository;
    public static Blurp blurp;
    public static BlurpScreen blurpScreen;
    public static FontHolder systemFont;
    public static OrthographicCamera gdxCamera;
    public static Effects effects;

    public static void initialise(BlurpConfiguration blurpConfiguration) {

        configuration = blurpConfiguration;

        if(!(blurpConfiguration.getViewport().getCamera() instanceof OrthographicCamera)) {
            throw new IllegalArgumentException("Viewport must have an OrthographicCamera");
        }
        gdxCamera = (OrthographicCamera) blurpConfiguration.getViewport().getCamera();

        modelCamera = new CameraImpl(screenCenterX(), screenCenterY());
        modelScreen = new Screen();
        effects = new EffectsImpl();

        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        Tween.registerAccessor(Camera.class, new CameraAccessor());
        tweener = new TweenManager();

        blurpifier = new Blurpifier();
        blurpScreen = new BlurpScreen(new RuntimeScreenRenderer());
        modelRepository = new ModelRepository();
        runtimeRepository = new RuntimeRepository();
        blurp = new BlurpImpl();
    }

    // Stuff that would normally go in the application.create() method, but can;t because we may be hosted in a
    // pre-existing libGdx app (i.e. PlanetBlurp).
    public static void onLibGdxInitialised() {

        Texture systemFontTexture = new Texture(Gdx.files.classpath("RobotoCondensed.png"), true);
        systemFontTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        BitmapFont font = new BitmapFont(Gdx.files.classpath("RobotoCondensed.fnt"),
                                         new TextureRegion(systemFontTexture), false);
        systemFont = new FontHolder(font);
    }

    public static double screenCenterX() {

        return configuration.getViewport().getWorldWidth() / 2;
    }

    public static double screenCenterY() {

        return configuration.getViewport().getWorldHeight() / 2;
    }
}
