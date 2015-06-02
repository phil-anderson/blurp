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
import com.bigcustard.blurp.model.Camera;
import com.bigcustard.blurp.model.Screen;
import com.bigcustard.blurp.model.Sprite;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.runtimemodel.*;
import com.bigcustard.blurp.ui.*;

/**
 * This is where all the singletons for the model and runtime get instantiated, before being passed on to the model and
 * runtime singletons stores. It's done in a separate class to give tests the opportunity to use something else.
 */
public class BlurpStore {

    public static MouseWindowChecker mouseWindowChecker;
    public static BlurpConfiguration configuration;
    public static ModelRepository modelRepository;
    public static Screen modelScreen;
    public static Camera modelCamera;
    public static Mouse modelMouse;
    public static TweenManager tweener;
    public static Blurpifier blurpifier;
    public static RuntimeRepository runtimeRepository;
    public static Blurp blurp;
    public static RuntimeScreen runtimeScreen;
    public static BlurpScreen blurpScreen;
    public static FontHolder defaultFont;
    public static BitmapFont systemFont;
    public static OrthographicCamera mainCamera;
    public static OrthographicCamera staticCamera;
    public static Effects effects;
    public static Console console;
    public static RuntimeConsole runtimeConsole;
    public static Keyboard keyboard;
    public static Utils utils;

    public static boolean debugMode;
    public static Color debugColour;

    public static void initialise(BlurpConfiguration blurpConfiguration, MouseWindowChecker mouseWindowChecker) {

        BlurpStore.mouseWindowChecker = mouseWindowChecker;
        configuration = blurpConfiguration;
        ScalingViewport viewport = blurpConfiguration.getViewport();

        if(!(viewport.getCamera() instanceof OrthographicCamera)) {
            throw new IllegalArgumentException("Viewport must have an OrthographicCamera");
        }
        mainCamera = (OrthographicCamera) viewport.getCamera();

        staticCamera = new OrthographicCamera();
        staticCamera.setToOrtho(false, viewport.getWorldWidth(), viewport.getWorldHeight());

        modelCamera = new CameraImpl(screenCenterX(), screenCenterY());
        modelScreen = new Screen(viewport.getWorldWidth(), viewport.getWorldHeight());
        keyboard = new KeyboardImpl();
        modelMouse = new MouseImpl();
        utils = new Utils();

        console = new ConsoleImpl();
        systemFont = new BitmapFont(Gdx.files.internal("small-rabbit.fnt"));
        systemFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        systemFont.setMarkupEnabled(true);
        runtimeConsole = new RuntimeConsole();

        effects = new EffectsImpl();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        Tween.registerAccessor(Camera.class, new CameraAccessor());
        tweener = new TweenManager();

        blurpifier = new Blurpifier();
        runtimeScreen = new RuntimeScreen();
        blurpScreen = new BlurpScreen();
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
        defaultFont = new FontHolder(font);
    }

    public static double screenCenterX() {

        return configuration.getViewport().getWorldWidth() / 2;
    }

    public static double screenCenterY() {

        return configuration.getViewport().getWorldHeight() / 2;
    }

    public static void syncSingletons() {

        runtimeScreen.sync();
        ((MouseImpl) modelMouse).sync();
    }

    public static void dispose() {

        tweener.killAll();
        blurpScreen.dispose();
        runtimeRepository.dispose();
        defaultFont.getFont().dispose();
        systemFont.dispose();
    }
}
