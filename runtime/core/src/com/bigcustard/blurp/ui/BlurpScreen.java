package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;

public class BlurpScreen extends ScreenAdapter {

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0f, 0f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
