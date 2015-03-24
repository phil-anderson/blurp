package com.bigcustard.blurp.core;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.ui.*;

public class Runner {

    private BlurpMain mainClass;
    private BlurpScreen screen;

    public Runner(BlurpMain mainClass, int width, int height) {

        this.mainClass = mainClass;
        screen = new BlurpScreen(width, height);
    }

    public BlurpScreen getScreen() {

        return screen;
    }

    public void play() {

    }
}
