package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;

public class Blurp extends Game {

    // TODO - Need to override lifecycle methods - Especially dispose.
    @Override
    public void create() {

        BlurpScreen screen = new BlurpScreen();
        setScreen(screen);
    }
}
