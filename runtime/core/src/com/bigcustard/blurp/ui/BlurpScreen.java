package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.util.*;

import static com.bigcustard.blurp.core.Blurpifier.*;

// TODO: Add an abstract immutable parent that can be exposed through BlurpRuntime.
public class BlurpScreen extends ScreenAdapter {

    private final Viewport viewport;
    private final RuntimeRepository runtimeRepository;
    private final RuntimeScreenRenderer runtimeScreenRenderer;
    private final Blurpifier blurpifier;
    private final BlurpObjectProvider blurpObjectProvider;

    private Batch batch;
    private Stage stage;
    private RenderListener renderListener = RenderListener.NULL_IMPLEMENTATION;
    private boolean firstRender = true;

    public BlurpScreen(Viewport viewport, RuntimeRepository runtimeRepository, RuntimeScreenRenderer runtimeScreenRenderer, Blurpifier blurpifier, BlurpObjectProvider blurpObjectProvider) {

        this.viewport = viewport;
        this.runtimeRepository = runtimeRepository;
        this.runtimeScreenRenderer = runtimeScreenRenderer;
        this.blurpifier = blurpifier;
        this.blurpObjectProvider = blurpObjectProvider;
    }

    public void addActor(Actor actor) {

        getStage().addActor(actor);
    }

    public RenderListener getRenderListener() {

        return renderListener;
    }

    public void onRenderEvent(RenderListener listener) {

        this.renderListener = listener != null ? listener : RenderListener.NULL_IMPLEMENTATION;
    }

    @Override
    public void render(float delta) {

        if(firstRender) {
            blurpObjectProvider.onLibGdxInitialised();
            batch = new SpriteBatch();
            stage = new Stage(viewport, batch);
            firstRender = false;
        }

        renderListener.handlePreRenderEvent(delta);

        blurpObjectProvider.getSystemFont().reset();

        try {
            doFrame(delta);
        } catch(RuntimeException exception) {
            // Pass it on so blurpify method can throw it
            blurpifier.setException(exception);
        }
        // TODO: I noticed that frames were being skipped in scripts that did virtually no processing between
        // frames. I put this sleep in to fix it although I'm not at all sure why it works. Render's just about to exit,
        // so I'd have thought teh script would get plenty of time between frames to do it's stuff.
        // Maybe libGdx isn't being particularly friendly outside of renders... Hmmmm...
        // I wonder if this will be an issue n Android?
        renderListener.handlePostRenderEvent(batch, delta);
        Utils.ENGINE_INSTANCE.sleep(1);
    }

    private void doFrame(float delta) {

        // TODO: We don't currently need this - Remove unless needed.
        getStage().act(delta);

        // Tweener update goes here too.

        synchronized(blurpifier) {
            if(blurpifier.getRequestState() == BlurpifyRequestState.Requested) {
                blurpifier.setRenderState(BlurpifyRenderState.RequestAcknowledged);
                try {
                    runtimeRepository.syncWithModelRepository(delta);
                } finally {
                    blurpifier.setRenderState(BlurpifyRenderState.RequestComplete);
                }
            } else {
                blurpifier.setRenderState(BlurpifyRenderState.NoRequest);
                // TODO: Remove when happy
//                System.out.println("SKIP!");
            }
        }

        doRender(delta);
    }

    private void doRender(float delta) {

        beginBatch(); // In case the RenderListener ended it.
        runtimeScreenRenderer.render();
        endBatch();

        getStage().draw();
    }

    private void beginBatch() {

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

        return stage;
    }

    public void enableDebug(boolean debugEnabled, Colour debugColour) {

        getStage().setDebugAll(debugEnabled);
        getStage().setDebugInvisible(debugEnabled);
        getStage().getDebugColor().set(Convert.toGdxColour(debugColour));
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
