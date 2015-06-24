package com.bigcustard.blurp.ui;

import java.lang.System;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.runtimemodel.*;
import com.bigcustard.blurp.util.*;

import static com.bigcustard.blurp.core.Blurpifier.*;

// TODO: Add an abstract immutable parent that can be exposed through BlurpRuntime.
public class BlurpScreen extends ScreenAdapter {

    private Batch batch;
    private ShapeRenderer shapes;
    private DebugHudRenderer debugHudRenderer;

    private Stage backgroundStage;
    private LayerStage mainStage;
    private LayerStage overlayStage;

    private RenderListener renderListener = RenderListener.NULL_IMPLEMENTATION;
    private boolean initialised = false;

    private int fps;
    private int fpsFrameCounter;
    private long lastFpsReading;

    private SystemShortcutHandler systemShortcutHandler = new SystemShortcutHandler();

    public void addActor(Actor actor) {

        mainStage.addActor(actor);
    }

    public RenderListener getRenderListener() {

        return renderListener;
    }

    public void onRenderEvent(RenderListener listener) {

        this.renderListener = listener != null ? listener : RenderListener.NULL_IMPLEMENTATION;
    }

    @Override
    public void resize(int width, int height) {

        BlurpStore.mainViewport.update(width, height);
        BlurpStore.staticViewport.update(width, height);
        BlurpStore.runtimeConsole.initialise();
    }

    @Override
    public void render(float delta) {

        // Don't move this to show()... Restart will die a horrible death.
        if(!initialised) {
            initialise();
        }

        BlurpState.frameStartTime = System.currentTimeMillis();
        try {
            systemShortcutHandler.check();
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
        try { Thread.sleep(1); } catch(InterruptedException e) { }
    }

    private void doFrame(float delta) {

        synchronized(BlurpStore.blurpifier) {

            if(BlurpStore.blurpifier.getRequestState() == BlurpifyRequestState.Requested) {
                BlurpStore.blurpifier.setRenderState(BlurpifyRenderState.RequestAcknowledged);
                try {
                    BlurpState.numFrames++;
                    updateFps();
                    updateCamera();
                    BlurpStore.runtimeRepository.syncWithModelRepository(delta);
                    BlurpStore.tweener.update(delta);
                    overlayStage.act(delta);
                    if(!overlayStage.mouseHandled()) {
                        mainStage.act(delta);
                        if(!mainStage.mouseHandled()) {
                            backgroundStage.act(delta);
                        }
                    }
                } finally {
                    BlurpStore.blurpifier.setRenderState(BlurpifyRenderState.RequestComplete);
                }
            } else {
                BlurpStore.blurpifier.setRenderState(BlurpifyRenderState.NoRequest);
            }
        }

        doRender();
    }

    private void updateFps() {

        long time = System.nanoTime();
        if(time - lastFpsReading > 1000000000) {
            fps = fpsFrameCounter;
            fpsFrameCounter = 0;
            lastFpsReading = time;
        }
        fpsFrameCounter++;
    }

    private void doRender() {

        beginBatch(); // In case the RenderListener ended it.
        BlurpStore.runtimeScreen.render();
        endBatch();

        backgroundStage.draw();
        mainStage.draw();
        overlayStage.draw();

        batch.setProjectionMatrix(BlurpStore.staticCamera.combined);
        beginBatch();
        BlurpStore.runtimeConsole.render(batch);
        endBatch();

        if(BlurpState.scriptComplete) {
            BlurpStore.scriptCompleteOverlay.render(batch, shapes);
        }

        if(BlurpState.debugMode) debugHudRenderer.render();
    }

    private void initialise() {

        batch = new SpriteBatch();
        shapes = new ShapeRenderer();
        debugHudRenderer = new DebugHudRenderer(batch, shapes);

        backgroundStage = new Stage(BlurpStore.staticViewport, batch);
        mainStage = new LayerStage(BlurpStore.mainViewport, batch);
        overlayStage = new LayerStage(BlurpStore.staticViewport, batch);

        Gdx.input.setInputProcessor(new InputMultiplexer(KeyboardImpl.KEYBOARD_PROCESSOR, overlayStage, mainStage, backgroundStage));

        if(Gdx.graphics.getBufferFormat().samples > 0) {
            Gdx.gl.glLineWidth(1.5f);
        } else  {
            Gdx.gl.glLineWidth(1f);
        }

        initialised = true;
    }

    private void updateCamera() {

        OrthographicCamera camera = BlurpStore.mainCamera;
        camera.rotate((float) BlurpStore.modelCamera.angle);
        camera.up.set(0, 1, 0);
        camera.direction.set(0, 0, -1);

        camera.position.set((float) BlurpStore.modelCamera.x, (float) BlurpStore.modelCamera.y, 0);
        camera.rotate((float) BlurpStore.modelCamera.angle);
        camera.zoom = (float) (1 / BlurpStore.modelCamera.zoom);
    }

    private void beginBatch() {

        if(!batch.isDrawing()) {
            batch.begin();
        }
        batch.setColor(Convert.toGdxColour(BlurpStore.modelCamera.colour, BlurpStore.modelCamera.transparency));
    }

    private void endBatch() {

        if(batch.isDrawing()) {
            batch.end();
        }
    }

    public void enableDebug(boolean debugEnabled, Colour debugColour) {

        backgroundStage.setDebugAll(debugEnabled);
        backgroundStage.setDebugInvisible(debugEnabled);
        backgroundStage.getDebugColor().set(Convert.toGdxColour(debugColour));

        mainStage.setDebugAll(debugEnabled);
        mainStage.setDebugInvisible(debugEnabled);
        mainStage.getDebugColor().set(Convert.toGdxColour(debugColour));

        overlayStage.setDebugAll(debugEnabled);
        overlayStage.setDebugInvisible(debugEnabled);
        overlayStage.getDebugColor().set(Convert.toGdxColour(debugColour));

        BlurpState.debugMode = debugEnabled;
        BlurpState.debugColour = debugColour;
    }

    public void handleSpriteLayer(RuntimeSprite sprite) {

        sprite.remove();
        if(sprite.getLayer() == ScreenLayer.Background) {
            backgroundStage.addActor(sprite);
        } else if(sprite.getLayer() == ScreenLayer.Overlay) {
            overlayStage.addActor(sprite);
        } else {
            mainStage.addActor(sprite);
        }
    }

    public void changeViewport(double width, double height, boolean stretch) {

        changeViewport(BlurpStore.mainViewport, width, height, stretch);
        changeViewport(BlurpStore.staticViewport, width, height, stretch);
    }

    private void changeViewport(ScalingViewport viewport, double width, double height, boolean stretch) {

        viewport.setWorldSize((float) width, (float) height);
        viewport.setScaling(stretch ? Scaling.stretch : Scaling.fit);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public int getFps() {

        return fps;
    }

    @Override
    public void dispose() {

        if(initialised) {
            batch.dispose();
            backgroundStage.dispose();
            mainStage.dispose();
            overlayStage.dispose();
            Gdx.input.setInputProcessor(null);
            initialised = false;
        }
    }
}
