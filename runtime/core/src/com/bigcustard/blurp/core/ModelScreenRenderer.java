package com.bigcustard.blurp.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.bigcustard.blurp.model.Screen;

public class ModelScreenRenderer {

    private final Screen modelScreen;

    public ModelScreenRenderer(com.bigcustard.blurp.model.Screen screen) {

        this.modelScreen = screen;
    }

    public void render() {

        Gdx.gl.glClearColor((float) modelScreen.backgroundColour.red,
                            (float) modelScreen.backgroundColour.green,
                            (float) modelScreen.backgroundColour.blue, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
