package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.ui.RenderListener.*;

import static com.bigcustard.blurp.core.Blurpifier.State.*;

// TODO: Add an abstract immutable parent that can be exposed through BlurpRuntime.
public class BlurpScreen extends ScreenAdapter {

    private final RuntimeScreenRenderer runtimeScreenRenderer;

    private RenderListener renderListener = RenderListenerAdapter.NULL_IMPLEMENTATION;

    private final Viewport viewport;
    private final Blurpifier blurpifier;
    private final RuntimeRepository runtimeRepository;
    private Batch batch;
    private Stage stage;

    public BlurpScreen(Viewport viewport, Blurpifier blurpifier, RuntimeRepository runtimeRepository, RuntimeScreenRenderer runtimeScreenRenderer) {

        this.viewport = viewport;
        this.blurpifier = blurpifier;
        this.runtimeRepository = runtimeRepository;
        this.runtimeScreenRenderer = runtimeScreenRenderer;
    }

    public void addActor(Actor actor) {

        getStage().addActor(actor);
    }

    public RenderListener getRenderListener() {

        return renderListener;
    }

    public void onRenderEvent(RenderListener listener) {

        this.renderListener = listener != null ? listener : RenderListenerAdapter.NULL_IMPLEMENTATION;
    }

    @Override
    public void render(float delta) {

        try {
            doFrame(delta);

            // TODO: I noticed that frames were being skipped in scripts that did virtually no processing between
            // frames. I put this sleep in to fix it although I'm no happy I fully understand why it was occurring.
            Thread.sleep(1);
        } catch(Exception e) {
            // Do nothing for now - Swallowing allows libgdx to continue rendering, and thus allows the app to be closed.
            // TODO: Rethrow it from the runner. UPDATE -  Should be able to do this now, just wrap in a BlurpExceotion
            e.printStackTrace();
        } finally {
            if(blurpifier.getState() == Requested) {
                blurpifier.setState(Complete);
            }
        }
    }

    private void doFrame(float delta) {

        renderListener.handleRenderEvent(batch, delta, EventType.PreFrame);

        // TODO: We don't currently need this - Remove unless needed.
        getStage().act(delta);

        // Tweener update goes here too.

        if(blurpifier.getState() == Requested) {
            runtimeRepository.syncWithModelRepository(delta);
        }
        doRender(delta);

        renderListener.handleRenderEvent(batch, delta, EventType.PostFrame);
    }

    private void doRender(float delta) {

        renderListener.handleRenderEvent(batch, delta, EventType.PreRender);

        beginBatch(); // In case the RenderListener ended it.
        runtimeScreenRenderer.render();
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

    @Override
    public void dispose() {

        if(batch != null) {
            batch.dispose();
        }

        if(stage != null) {
            stage.dispose();
        }
    }
}
