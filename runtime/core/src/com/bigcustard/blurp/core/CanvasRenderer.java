package com.bigcustard.blurp.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.bigcustard.blurp.model.Canvas;

public class CanvasRenderer {

    public void render() {

        Canvas canvas = SF.getRuntimeRepository().getCanvas();

        Gdx.gl.glClearColor((float) canvas.backgroundColour.red,
                            (float) canvas.backgroundColour.green,
                            (float) canvas.backgroundColour.blue, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
