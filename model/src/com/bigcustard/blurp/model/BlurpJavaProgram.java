package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.effects.*;

public abstract class BlurpJavaProgram implements BlurpRunnable, Runnable {

    protected Blurp blurp;
    protected Screen screen;
    protected Console console;
    protected Camera camera;
    protected Effects effects;
    protected Keyboard keyboard;
    protected Mouse mouse;
    protected Utils utils;

    @Override
    public void run(Blurp blurp, Screen screen, Console console, Camera camera, Effects effects, Keyboard keyboard, Mouse mouse, Utils utils) {

        this.blurp = blurp;
        this.screen = screen;
        this.console = console;
        this.camera = camera;
        this.effects = effects;
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.utils = utils;

        run();
    }
}
