package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.constants.*;

public abstract class System {

    public final Log log;

    protected System(Log log) {

        this.log = log;
    }

    public System debugMode(boolean enable) {

        debugMode(enable, Colours.LIME_GREEN);
        return this;
    }

    public abstract System debugMode(boolean enable, Colour debugColour);

    public abstract int getFps();

    public abstract void stop();

    public abstract void restart();

    public abstract void terminate();
}
