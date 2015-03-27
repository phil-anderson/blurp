package com.bigcustard.blurp.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.bigcustard.blurp.model.Screen;

public class BackdropRenderer {

    public void render() {

        Screen screen = RuntimeRepository.getInstance().getBackdrop();

        Gdx.gl.glClearColor((float) screen.backgroundColour.red,
                            (float) screen.backgroundColour.green,
                            (float) screen.backgroundColour.blue, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
