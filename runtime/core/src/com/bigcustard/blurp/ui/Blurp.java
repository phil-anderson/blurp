package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class Blurp extends Game {

    private BlurpMain script;
    private int width, height;

    public Blurp(BlurpMain script, int width, int height) {

        this.script = script;
        this.width = width;
        this.height = height;
    }

    // TODO - Need to override other lifecycle methods
    @Override
    public void create() {

        Runner runner = new Runner(script, width, height);
        runner.start();
        setScreen(runner.getScreen());
    }
}
