package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.ui.RenderListener.*;

import static com.bigcustard.blurp.core.Blurpifier.State.*;

public class BlurpScreen extends ScreenAdapter {

    private final ModelScreenRenderer modelScreenRenderer;

    private RenderListener renderListener = RenderListenerAdapter.NULL_IMPLEMENTATION;

    private final Viewport viewport;
    private final Blurpifier blurpifier;
    private final RuntimeRepository runtimeRepository;
    private Batch batch;
    private Stage stage;

    public BlurpScreen(Viewport viewport, Blurpifier blurpifier, RuntimeRepository runtimeRepository, ModelScreenRenderer modelScreenRenderer) {

        this.viewport = viewport;
        this.blurpifier = blurpifier;
        this.runtimeRepository = runtimeRepository;
        this.modelScreenRenderer = modelScreenRenderer;
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
            doFrame(delta);
        } catch(Exception e) {
            // Do nothing for now - Swallowing allows libgdx to continue rendering, and thus allows the app to be closed.
            // TODO: Rethrow it from the runner
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

        // TODO: We don't currently need this - Remove unless needed.
        getStage().act(delta);

        // Tweener update goes here too.

        if(blurpifier.getState() == Requested) {
            try {
                doRender(delta);
            } finally {
                // This should be done at very end of render.
                blurpifier.setState(Complete);
            }
        }

        renderListener.handleRenderEvent(batch, delta, EventType.PostFrame);
    }

    private void doRender(float delta) {

        renderListener.handleRenderEvent(batch, delta, EventType.PreRender);

            runtimeRepository.syncWithModelRepository(delta);

            beginBatch(); // In case the RenderListener ended it.
            modelScreenRenderer.render();
            endBatch();

            getStage().draw();

            renderListener.handleRenderEvent(batch, delta, EventType.PostRender);
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

    public void enableDebug(boolean debugHidden) {

        getStage().setDebugAll(true);
        getStage().setDebugInvisible(debugHidden);
    }

    public void disableDebug() {

        getStage().setDebugAll(false);
        getStage().setDebugInvisible(false);
    }
}
