package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
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

    private Batch batch;
    private Stage stage;
    private RenderListener renderListener = RenderListener.NULL_IMPLEMENTATION;
    private boolean firstRender = true;

    public BlurpScreen() {

        this.viewport = BlurpStore.configuration.getViewport();
    }

    public void addActor(Actor actor) {

        stage.addActor(actor);
    }

    public RenderListener getRenderListener() {

        return renderListener;
    }

    public void onRenderEvent(RenderListener listener) {

        this.renderListener = listener != null ? listener : RenderListener.NULL_IMPLEMENTATION;
    }

    @Override
    public void render(float delta) {

        try {
            if(firstRender) {
                initialise();
            }

            renderListener.handlePreRenderEvent(delta);
            BlurpStore.defaultFont.reset();
            doFrame(delta);

        } catch(RuntimeException exception) {
            // Pass it on so blurpify method can throw it
            BlurpStore.blurpifier.setException(exception);
        }
        renderListener.handlePostRenderEvent(batch, delta);

        // TODO: I noticed that frames were being skipped in scripts that did virtually no processing between
        // frames. I put this sleep in to fix it although I'm not at all sure why it works. Render's just about to exit,
        // so I'd have thought teh script would get plenty of time between frames to do it's stuff.
        // Maybe libGdx isn't being particularly friendly outside of renders... Hmmmm...
        // I wonder if this will be an issue n Android?
        try { Thread.sleep(1); } catch(InterruptedException e) { e.printStackTrace(); }
    }

    private void doFrame(float delta) {

        synchronized(BlurpStore.blurpifier) {
            if(BlurpStore.blurpifier.getRequestState() == BlurpifyRequestState.Requested) {
                BlurpStore.blurpifier.setRenderState(BlurpifyRenderState.RequestAcknowledged);
                try {
                    updateCamera();
                    BlurpStore.runtimeRepository.syncWithModelRepository(delta);
                    BlurpStore.tweener.update(delta);
                    stage.act(delta);
                } finally {
                    BlurpStore.blurpifier.setRenderState(BlurpifyRenderState.RequestComplete);
                }
            } else {
                BlurpStore.blurpifier.setRenderState(BlurpifyRenderState.NoRequest);
                // TODO: Remove when happy
//                System.out.println("SKIP!");
            }
        }

        doRender();
    }

    private void doRender() {

        beginBatch(); // In case the RenderListener ended it.
        BlurpStore.runtimeScreen.render();
        endBatch();

        stage.draw();

        resetCamera();
        beginBatch();
        BlurpStore.runtimeConsole.render(batch);
        endBatch();
    }

    private void initialise() {

        batch = new SpriteBatch();
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        Gdx.gl.glLineWidth(1.5f);

        firstRender = false;

        BlurpStore.onLibGdxInitialised();
    }

    private void updateCamera() {

        OrthographicCamera camera = BlurpStore.gdxCamera;
        camera.rotate((float) BlurpStore.modelCamera.rotation);
        camera.up.set(0, 1, 0);
        camera.direction.set(0, 0, -1);

        camera.position.set((float) BlurpStore.modelCamera.x, (float) BlurpStore.modelCamera.y, 0);
        camera.rotate((float) BlurpStore.modelCamera.rotation);
        camera.zoom = (float) (1 / BlurpStore.modelCamera.zoom);
    }

    private void resetCamera() {

        OrthographicCamera camera = BlurpStore.gdxCamera;
        camera.rotate((float) BlurpStore.modelCamera.rotation);
        camera.up.set(0, 1, 0);
        camera.direction.set(0, 0, -1);

        camera.position.set((float) BlurpStore.screenCenterX(), (float) BlurpStore.screenCenterY(), 0);
        camera.zoom = (float) (1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    private void beginBatch() {

        if(!batch.isDrawing()) {
            batch.begin();
        }
        batch.setColor(Convert.toGdxColour(BlurpStore.modelCamera.colour, BlurpStore.modelCamera.alpha));
    }

    private void endBatch() {

        if(batch.isDrawing()) {
            batch.end();
        }
    }

    public void enableDebug(boolean debugEnabled, Colour debugColour) {

        stage.setDebugAll(debugEnabled);
        stage.setDebugInvisible(debugEnabled);
        stage.getDebugColor().set(Convert.toGdxColour(debugColour));
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
