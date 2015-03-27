package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.core.*;

// TODO: This is an externally instantiated singleton. I'm not wild about it, but it's better than anything else I
//       could think of. I think Spencer will really hate it though.
public class BlurpScreen extends ScreenAdapter {

    private static BlurpScreen instance;

    private final Stage stage;
    private final Viewport viewport;
    private final BackdropRenderer backdropRenderer;

    private RenderListener renderListener = RenderListenerAdapter.NULL_IMPLEMENTATION;

    public BlurpScreen(int width, int height) {

        if(BlurpScreen.instance != null) throw new IllegalStateException("Can only have one BlurpScreen");

        viewport = new FitViewport(width, height);
        stage = new Stage(viewport);
        backdropRenderer = new BackdropRenderer();
        BlurpScreen.instance = this;
    }

    public static BlurpScreen getInstance() {

        return instance;
    }

    @Override
    public void render(float delta) {

        try {
            Batch batch = beginBatch();
            doFrame(batch, delta);
            endBatch();
        } catch(Exception e) {
            // Do nothing for now - Swallowing allows libgdx to continue rendering, and thus allows the app to be closed.
            // TODO: Something more appropriate.
            e.printStackTrace();
        }
    }

    private void doFrame(Batch batch, float delta) {

        renderListener.handleRenderEvent(batch, delta, RenderListener.EventType.PreFrame);

        stage.act(delta);
        // Tweener update goes here too.

        if(RuntimeRepository.getInstance().getBlurpifier().getState() == Blurpifier.State.Requested) {
            doRender(batch, delta);
        }

        renderListener.handleRenderEvent(batch, delta, RenderListener.EventType.PostFrame);
    }

    private void doRender(Batch batch, float delta) {

        renderListener.handleRenderEvent(batch, delta, RenderListener.EventType.PreFrame);

        try {

            RuntimeRepository.getInstance().syncWithModelRepository();

            beginBatch(); // In case a RenderListener ended it.
            backdropRenderer.render();
            endBatch();

            stage.draw();

        } finally {
            RuntimeRepository.getInstance().getBlurpifier().setState(Blurpifier.State.Complete);
        }

        renderListener.handleRenderEvent(batch, delta, RenderListener.EventType.PostFrame);
    }

    private Batch beginBatch() {

        Batch batch = stage.getBatch();
        if(!batch.isDrawing()) {
            batch.begin();
        }
        return batch;
    }

    private void endBatch() {

        Batch batch = stage.getBatch();
        if(batch.isDrawing()) {
            batch.end();
        }
    }

    public RenderListener getRenderListener() {

        return renderListener;
    }

    public void setRenderListener(RenderListener listener) {

        this.renderListener = listener != null ? listener : RenderListenerAdapter.NULL_IMPLEMENTATION;
    }

    public Stage getStage() {

        return stage;
    }

    @Override
    public void dispose() {

        BlurpScreen.instance = null;
    }
}
