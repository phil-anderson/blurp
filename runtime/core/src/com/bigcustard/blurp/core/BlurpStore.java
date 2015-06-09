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

    public static BlurpRuntime runtime;
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
    public static ScalingViewport mainViewport;
    public static ScalingViewport staticViewport;
    public static Effects effects;
    public static Console console;
    public static RuntimeConsole runtimeConsole;
    public static Keyboard keyboard;
    public static Utils utils;

    public static void initialise(BlurpConfiguration configuration, MouseWindowChecker mouseWindowChecker, BlurpRuntime runtime) {

        float worldWidth = (float) configuration.getInitialViewportWidth();
        float worldHeight = (float) configuration.getInitialViewportHeight();

        BlurpStore.mouseWindowChecker = mouseWindowChecker;
        BlurpStore.runtime = runtime;
        BlurpStore.configuration = configuration;

        mainCamera = new OrthographicCamera();
        mainCamera.setToOrtho(false, worldWidth, worldHeight);
        mainViewport = new FitViewport(worldWidth, worldHeight, mainCamera);

        staticCamera = new OrthographicCamera();
        staticCamera.setToOrtho(false, worldWidth, worldHeight);
        staticViewport = new FitViewport(worldWidth, worldHeight, staticCamera);

        modelCamera = new CameraImpl(worldWidth / 2, worldHeight / 2);
        modelScreen = new ScreenImpl(worldWidth, worldHeight);
        keyboard = new KeyboardImpl();
        modelMouse = new MouseImpl();
        utils = new Utils();

        initialiseFonts();

        console = new ConsoleImpl();
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
    public static void initialiseFonts() {

        systemFont = new BitmapFont(Gdx.files.internal("small-rabbit.fnt"));
        systemFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        systemFont.setMarkupEnabled(true);

        Texture systemFontTexture = new Texture(Gdx.files.classpath("RobotoCondensed.png"), true);
        systemFontTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        BitmapFont font = new BitmapFont(Gdx.files.classpath("RobotoCondensed.fnt"), new TextureRegion(systemFontTexture), false);
        defaultFont = new FontHolder(font);
    }

    public static void syncSingletons() {

        runtimeScreen.sync();
        ((MouseImpl) modelMouse).sync();
    }

    public static void reset() {

        dispose();

        mainCamera = new OrthographicCamera();
        mainCamera.setToOrtho(false, (float) configuration.getInitialViewportWidth(), (float) configuration.getInitialViewportHeight());
        mainViewport = new FitViewport((float) configuration.getInitialViewportWidth(), (float) configuration.getInitialViewportHeight(), mainCamera);

        staticCamera = new OrthographicCamera();
        staticCamera.setToOrtho(false, (float) configuration.getInitialViewportWidth(), (float) configuration.getInitialViewportHeight());
        staticViewport = new FitViewport((float) configuration.getInitialViewportWidth(), (float) configuration.getInitialViewportHeight(), staticCamera);

        runtimeScreen = new RuntimeScreen();
        runtimeConsole = new RuntimeConsole();

        initialiseFonts();

        blurpifier.reset();
        ((ConsoleImpl) console).reset();
        ((CameraImpl) modelCamera).reset();
        ((EffectsImpl) effects).reset();
        ((ScreenImpl) modelScreen).reset(configuration.getInitialViewportWidth(), (float) configuration.getInitialViewportHeight());
        BlurpState.reset();
    }

    public static void dispose() {

        tweener.killAll();
        tweener.update(9999);
        blurpScreen.dispose();
        modelRepository.dispose();
        runtimeRepository.dispose();
        defaultFont.dispose();
        systemFont.dispose();
    }
}
