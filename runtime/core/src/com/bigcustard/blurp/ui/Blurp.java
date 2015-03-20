package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;

public class Blurp extends Game {

    // TODO - Need to override lifecycle methods
    @Override
    public void create() {

        BlurpScreen screen = new BlurpScreen(800, 600);
        setScreen(screen);
    }
}
