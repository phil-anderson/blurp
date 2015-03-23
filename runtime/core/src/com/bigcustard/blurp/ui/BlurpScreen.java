package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.runtimemodel.*;

public class BlurpScreen extends ScreenAdapter {

    private final Stage stage;
    private final Viewport viewport;
    private final BackdropRenderer backdropRenderer;
    private RenderListener onPreFrame;
    private RenderListener onPreRender;
    private RenderListener onPostRender;
    private RenderListener onPostFrame;

    public BlurpScreen(int width, int height) {

        viewport = new FitViewport(width, height);
        stage = new Stage(viewport);
        backdropRenderer = new BackdropRenderer();

    }

    @Override
    public void render(float delta) {

        Batch batch = stage.getBatch();
        batch.begin();
        doFrame(batch, delta);
        if(batch.isDrawing()) batch.end();
    }

    public void onPreFrame(RenderListener onPreFrame) {

        this.onPreFrame = onPreFrame;
    }

    public void onPreRender(RenderListener onPreRender) {

        this.onPreRender = onPreRender;
    }

    public void onPostRender(RenderListener onPostRender) {

        this.onPostRender = onPostRender;
    }

    public void onPostFrame(RenderListener onPostFrame) {

        this.onPostFrame = onPostFrame;
    }


    private void doFrame(Batch batch, float delta) {

        if(onPreFrame != null)  onPreFrame.handleRenderEvent(batch, delta, RenderListener.EventType.PreFrame);

        stage.act(delta);
        // Tweener update goes here too.

        if(RuntimeRepository.getInstance().getBlurpifier().getState() == Blurpifier.State.Requested) {
            doRender(batch, delta);
        }
        if(!batch.isDrawing()) batch.begin(); // Restart spritebatch in case it was ended.

        if(onPostFrame != null)  onPostFrame.handleRenderEvent(batch, delta, RenderListener.EventType.PostFrame);
    }

    private void doRender(Batch batch, float delta) {

        if(onPreRender != null)  onPreRender.handleRenderEvent(batch, delta, RenderListener.EventType.PreFrame);

        try {
            RuntimeRepository.getInstance().syncWithModelRepository();
            backdropRenderer.render();
        } finally {
            RuntimeRepository.getInstance().getBlurpifier().setState(Blurpifier.State.Complete);
        }

        if(onPostRender != null)  onPostRender.handleRenderEvent(batch, delta, RenderListener.EventType.PostFrame);
    }
}
