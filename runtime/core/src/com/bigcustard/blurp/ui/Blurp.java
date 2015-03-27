package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class Blurp extends Game {

    private BlurpMain script;
    private Viewport viewport;

    public Blurp(BlurpMain script, Viewport viewport) {

        this.script = script;
        this.viewport = viewport;
    }

    // TODO - Need to override other lifecycle methods
    @Override
    public void create() {

        Runner runner = new Runner(script, viewport);
        runner.start();
        setScreen(runner.getScreen());
    }
}
