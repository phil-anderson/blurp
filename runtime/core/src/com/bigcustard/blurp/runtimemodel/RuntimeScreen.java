package com.bigcustard.blurp.runtimemodel;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.Screen;

public class RuntimeScreen {

    private Colour backgroundColour;
    public double viewportWidth, viewportHeight;
    public boolean viewportStretch;

    public void sync() {

        Screen modelScreen = BlurpStore.modelScreen;

        this.backgroundColour = modelScreen.backgroundColour;

        if(viewportWidth != modelScreen.viewportWidth || viewportHeight != modelScreen.viewportHeight || viewportStretch != modelScreen.viewportStretch) {
            viewportWidth = modelScreen.viewportWidth;
            viewportHeight = modelScreen.viewportHeight;
            viewportStretch = modelScreen.viewportStretch;
            BlurpStore.blurpScreen.changeViewport(viewportWidth, viewportHeight, viewportStretch);
        }
    }

    public void render() {

        Gdx.gl.glClearColor((float) backgroundColour.red,
                            (float) backgroundColour.green,
                            (float) backgroundColour.blue, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
