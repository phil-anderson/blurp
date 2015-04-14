package com.bigcustard.blurp.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.runtimemodel.*;

public class RuntimeScreenRenderer {

    private final RuntimeScreen screen;

    public RuntimeScreenRenderer(RuntimeScreen screen) {

        this.screen = screen;
    }

    public void render() {

        Colour backgroundColour = screen.getBackgroundColour();
        Gdx.gl.glClearColor((float) backgroundColour.red,
                            (float) backgroundColour.green,
                            (float) backgroundColour.blue, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
