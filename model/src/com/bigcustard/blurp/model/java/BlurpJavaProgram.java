package com.bigcustard.blurp.model.java;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.System;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.java.bootstrap.*;

public abstract class BlurpJavaProgram implements Runnable, Colours, SpriteEventHandlers {

    public static JavaBootstrap javaBootstrap;

    protected final System system;
    protected final Screen screen;
    protected final Camera camera;
    protected final Console console;
    protected final Resources resources;
    protected final Effects effects;
    protected final Keyboard keyboard;
    protected final Mouse mouse;
    protected final Timer timer;
    protected final Utils utils;

    protected BlurpJavaProgram() {

        JavaBootstrap javaBootstrap = JavaBootstrapHolder.getInstance();

        system = javaBootstrap.getSystem();
        screen = javaBootstrap.getScreen();
        camera = javaBootstrap.getCamera();
        console = javaBootstrap.getConsole();
        resources = javaBootstrap.getResources();
        effects = javaBootstrap.getEffects();
        keyboard = javaBootstrap.getKeyboard();
        mouse = javaBootstrap.getMouse();
        timer = javaBootstrap.getTimer();
        utils = javaBootstrap.getUtils();
    }
}
