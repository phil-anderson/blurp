package com.bigcustard.blurp.bootstrap;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.System;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.java.bootstrap.*;

public class JavaBootstrapImpl implements JavaBootstrap {

    @Override
    public System getSystem() {

        return BlurpStore.system;
    }

    @Override
    public Screen getScreen() {

        return BlurpStore.modelScreen;
    }

    @Override
    public Camera getCamera() {

        return BlurpStore.modelCamera;
    }

    @Override
    public Console getConsole() {

        return BlurpStore.console;
    }

    @Override
    public Resources getResources() { return BlurpStore.resources; }

    @Override
    public Effects getEffects() {

        return BlurpStore.effects;
    }

    @Override
    public Keyboard getKeyboard() {

        return BlurpStore.keyboard;
    }

    @Override
    public Mouse getMouse() {

        return BlurpStore.modelMouse;
    }

    @Override
    public Timer getTimer() {

        return BlurpStore.timer;
    }

    @Override
    public Utils getUtils() {

        return BlurpStore.utils;
    }
}
