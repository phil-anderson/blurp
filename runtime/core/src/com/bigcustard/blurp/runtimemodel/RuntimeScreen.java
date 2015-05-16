package com.bigcustard.blurp.runtimemodel;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.Colours;

public class RuntimeScreen {

    private Colour backgroundColour = Colours.BLACK;

    public void sync() {

        this.backgroundColour = BlurpStore.modelScreen.backgroundColour;
    }

    public void render() {

        Gdx.gl.glClearColor((float) backgroundColour.red,
                            (float) backgroundColour.green,
                            (float) backgroundColour.blue, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
