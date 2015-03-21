package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.*;

public class BlurpScreen extends ScreenAdapter {

    private final Stage stage;
    private Viewport viewport;

    public BlurpScreen(int width, int height) {

        viewport = new FitViewport(width, height);
        stage = new Stage(viewport);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0f, 0f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
