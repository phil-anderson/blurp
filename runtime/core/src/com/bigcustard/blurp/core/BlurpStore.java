package com.bigcustard.blurp.core;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.core.effects.*;
import com.bigcustard.blurp.model.Camera;
import com.bigcustard.blurp.model.Sprite;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.Viewport;
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
    public static Viewport modelViewport;
    public static ScreenImpl modelScreen;
    public static CameraImpl modelCamera;
    public static MouseImpl modelMouse;
    public static TweenManager tweener;
    public static Blurpifier blurpifier;
    public static RuntimeRepository runtimeRepository;
    public static ImageCache imageCache;

    public static ConsoleImpl console;
    public static SystemImpl system;
    public static RuntimeScreen runtimeScreen;
    public static ScriptCompleteOverlay scriptCompleteOverlay;
    public static BlurpScreen blurpScreen;
    public static FontHolder defaultFont;
    public static BitmapFont systemFont;
    public static OrthographicCamera mainCamera;
    public static OrthographicCamera staticCamera;
    public static ScalingViewport mainViewport;
    public static ScalingViewport staticViewport;
    public static ResourcesImpl resources;
    public static EffectsImpl effects;
    public static RuntimeConsole runtimeConsole;
    public static KeyboardImpl keyboard;
    public static TimerImpl timer;
    public static Utils utils;

    public static void initialise(BlurpConfiguration configuration, MouseWindowChecker mouseWindowChecker, BlurpRuntime runtime) {

        float worldWidth = (float) configuration.getInitialViewportWidth();
        float worldHeight = (float) configuration.getInitialViewportHeight();

        BlurpStore.mouseWindowChecker = mouseWindowChecker;
        BlurpStore.runtime = runtime;
        BlurpStore.configuration = configuration;

        initialiseViewports();

        modelCamera = new CameraImpl(worldWidth / 2, worldHeight / 2);
        modelViewport = new Viewport().setSize(worldWidth, worldHeight);
        modelScreen = new ScreenImpl();
        keyboard = new KeyboardImpl();
        modelMouse = new MouseImpl();
        timer = new TimerImpl();
        utils = new Utils();

        initialiseFonts();

        resources = new ResourcesImpl();
        effects = new EffectsImpl();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        Tween.registerAccessor(Camera.class, new CameraAccessor());
        tweener = new TweenManager();

        blurpifier = new Blurpifier();
        runtimeScreen = new RuntimeScreen();
        scriptCompleteOverlay = new ScriptCompleteOverlay();
        blurpScreen = new BlurpScreen();
        modelRepository = new ModelRepository();
        runtimeRepository = new RuntimeRepository();
        imageCache = new ImageCache();

        console = new ConsoleImpl();
        runtimeConsole = new RuntimeConsole();
        system = new SystemImpl();
    }

    // Stuff that would normally go in the application.create() method, but can;t because we may be hosted in a
    // pre-existing libGdx app (i.e. PlanetBlurp).
    public static void initialiseFonts() {

        systemFont = new BitmapFont(Gdx.files.internal("system-font.fnt"));
        systemFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        systemFont.getData().markupEnabled = true;

        Texture systemFontTexture = new Texture(Gdx.files.classpath("RobotoCondensed.png"), true);
        systemFontTexture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
        BitmapFont font = new BitmapFont(Gdx.files.classpath("RobotoCondensed.fnt"), new TextureRegion(systemFontTexture), false);
        defaultFont = new FontHolder(font);
    }

    public static void syncSingletons() {

        runtimeScreen.sync();
        modelMouse.sync();
        keyboard.sync();
    }

    public static void reset() {

        tweener.killAll();
        tweener.update(9999);
        blurpScreen.dispose();
        modelRepository.dispose();
        runtimeRepository.dispose();
        defaultFont.dispose();
        systemFont.dispose();
        imageCache.clear();

        initialiseViewports();

        runtimeScreen = new RuntimeScreen();
        runtimeConsole = new RuntimeConsole();

        initialiseFonts();

        blurpifier.reset();
        console.reset();
        modelCamera.reset();
        effects.reset();
        modelScreen.reset(configuration.getInitialViewportWidth(), (float) configuration.getInitialViewportHeight());
        timer.reset();
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
        scriptCompleteOverlay.dispose();
        imageCache.clear();
    }

    private static void initialiseViewports() {

        mainCamera = new OrthographicCamera();
        mainCamera.setToOrtho(false, (float) configuration.getInitialViewportWidth(), (float) configuration.getInitialViewportHeight());
        mainViewport = new FitViewport((float) configuration.getInitialViewportWidth(), (float) configuration.getInitialViewportHeight(), mainCamera);
        mainViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        staticCamera = new OrthographicCamera();
        staticCamera.setToOrtho(false, (float) configuration.getInitialViewportWidth(), (float) configuration.getInitialViewportHeight());
        staticViewport = new FitViewport((float) configuration.getInitialViewportWidth(), (float) configuration.getInitialViewportHeight(), staticCamera);
        staticViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
