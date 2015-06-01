package com.bigcustard.blurp.model.java;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.java.bootstrap.*;

public abstract class BlurpJavaProgram implements Runnable {

    public static BlurpBootstrap blurpBootstrap;

    protected final Blurp blurp;
    protected final Screen screen;
    protected final Console console;
    protected final Camera camera;
    protected final Effects effects;
    protected final Keyboard keyboard;
    protected final Mouse mouse;
    protected final Utils utils;

    protected BlurpJavaProgram() {

        BlurpBootstrap blurpBootstrap = BlurpBootstrapHolder.getInstance();

        blurp = blurpBootstrap.getBlurp();
        screen = blurpBootstrap.getScreen();
        console = blurpBootstrap.getConsole();
        camera = blurpBootstrap.getCamera();
        effects = blurpBootstrap.getEffects();
        keyboard = blurpBootstrap.getKeyboard();
        mouse = blurpBootstrap.getMouse();
        utils = blurpBootstrap.getUtils();
    }
}
