package com.bigcustard.blurp.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.bigcustard.blurp.model.*;

public class RuntimeScreenRenderer {

    public void render() {

        Colour backgroundColour = BlurpStore.modelScreen.backgroundColour;

        Gdx.gl.glClearColor((float) backgroundColour.red,
                            (float) backgroundColour.green,
                            (float) backgroundColour.blue, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
