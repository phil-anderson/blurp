package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.ui.RenderListener.*;

import static com.bigcustard.blurp.core.Blurpifier.State.*;

public class BlurpScreen extends ScreenAdapter {

    private final CanvasRenderer canvasRenderer;

    private RenderListener renderListener = RenderListenerAdapter.NULL_IMPLEMENTATION;

    private Viewport viewport;
    private Batch batch;
    private Stage stage;

    public BlurpScreen(Viewport viewport) {

        this.viewport = viewport;
        canvasRenderer = new CanvasRenderer();
    }

    public void addActor(Actor actor) {

        getStage().addActor(actor);
    }

    public RenderListener getRenderListener() {

        return renderListener;
    }

    public void setRenderListener(RenderListener listener) {

        this.renderListener = listener != null ? listener : RenderListenerAdapter.NULL_IMPLEMENTATION;
    }

    @Override
    public void render(float delta) {

        try {
            beginBatch();
            doFrame(delta);
            endBatch();
        } catch(Exception e) {
            // Do nothing for now - Swallowing allows libgdx to continue rendering, and thus allows the app to be closed.
            // TODO: Something more appropriate.
            e.printStackTrace();
        }
    }

    @Override
    public void dispose() {

        if(batch != null) {
            batch.dispose();
        }

        if(stage != null) {
            stage.dispose();
        }
    }

    private void doFrame(float delta) {

        renderListener.handleRenderEvent(batch, delta, EventType.PreFrame);

        getStage().act(delta);

        // Tweener update goes here too.

        if(SF.getBlurpifier().getState() == Requested) {
            doRender(delta);
        }

        renderListener.handleRenderEvent(batch, delta, EventType.PostFrame);
    }

    private void doRender(float delta) {

        renderListener.handleRenderEvent(batch, delta, EventType.PreRender);

        try {
            SF.getRuntimeRepository().syncWithModelRepository();

            beginBatch(); // In case the RenderListener ended it.
            canvasRenderer.render();
            endBatch();

            getStage().draw();

        } finally {
            SF.getBlurpifier().setState(Complete);
        }

        beginBatch();
        renderListener.handleRenderEvent(batch, delta, EventType.PostRender);
        endBatch();
    }

    private void beginBatch() {

        lazyInitialise();
        if(!batch.isDrawing()) {
            batch.begin();
        }
    }

    private void endBatch() {

        if(batch.isDrawing()) {
            batch.end();
        }
    }

    private Stage getStage() {

        lazyInitialise();
        return stage;
    }

    private void lazyInitialise() {

        // Lazily initialise to give libgdx a chance to start up
        if(batch == null) {
            batch = new SpriteBatch();
        }

        if(stage == null) {
            stage = new Stage(viewport, batch);
        }
    }
}
