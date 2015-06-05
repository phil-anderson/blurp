package com.bigcustard.blurp.model.java;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.java.bootstrap.*;

public abstract class BlurpJavaProgram implements Runnable {

    public static JavaBootstrap javaBootstrap;

    protected final Blurp blurp;
    protected final Screen screen;
    protected final Console console;
    protected final Camera camera;
    protected final Effects effects;
    protected final Keyboard keyboard;
    protected final Mouse mouse;
    protected final Utils utils;

    protected BlurpJavaProgram() {

        JavaBootstrap javaBootstrap = JavaBootstrapHolder.getInstance();

        blurp = javaBootstrap.getBlurp();
        screen = javaBootstrap.getScreen();
        console = javaBootstrap.getConsole();
        camera = javaBootstrap.getCamera();
        effects = javaBootstrap.getEffects();
        keyboard = javaBootstrap.getKeyboard();
        mouse = javaBootstrap.getMouse();
        utils = javaBootstrap.getUtils();
    }
}
