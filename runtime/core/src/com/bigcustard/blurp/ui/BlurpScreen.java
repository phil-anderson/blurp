package com.bigcustard.blurp.ui;

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

    private final ScalingViewport viewport;
    private final ScalingViewport staticViewport;

    private Batch batch;
    private ShapeRenderer shapes;
    private DebugHudRenderer debugHudRenderer;

    private Stage backgroundStage;
    private LayerStage mainStage;
    private LayerStage overlayStage;

    private RenderListener renderListener = RenderListener.NULL_IMPLEMENTATION;
    private boolean initialised = false;

    public BlurpScreen() {

        this.viewport = BlurpStore.configuration.getViewport();
        this.staticViewport = new ScalingViewport(viewport.getScaling(), viewport.getWorldWidth(), viewport.getWorldHeight(), BlurpStore.staticCamera);
    }

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
    public void render(float delta) {

        try {
            if(!initialised) {
                initialise();
            }

            renderListener.handlePreRenderEvent(delta);
            BlurpStore.defaultFont.reset();
            doFrame(delta);

            if(BlurpState.scriptComplete) {
                scriptComplete();
            }
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
        BlurpStore.utils.sleep(1);
    }

    // TODO: Temporary (and a bit hacky) keyboard-based controls. Need to build something mouse-based.
    private void scriptComplete() {

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        float textHeight = BlurpStore.staticCamera.viewportHeight / 20;

        shapes.setColor(0, 0, 0, 0.75f);
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.rect(0, 0, BlurpStore.staticCamera.viewportWidth, textHeight * 1.2f);
        shapes.end();

        shapes.setColor(Color.GRAY);
        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.line(0, textHeight * 1.2f, BlurpStore.staticCamera.viewportWidth, textHeight * 1.2f);
        shapes.end();

        BlurpStore.defaultFont.reset();
        BitmapFont font = BlurpStore.defaultFont.getFont();
        font.setScale(textHeight / font.getLineHeight());
        font.setColor(Color.LIGHT_GRAY);

        batch.setProjectionMatrix(BlurpStore.staticCamera.combined);
        batch.begin();
        font.drawWrapped(batch, "Program Complete - Press SPACE to restart, ESC to exit", 0, textHeight, BlurpStore.staticCamera.viewportWidth, BitmapFont.HAlignment.CENTER);
        batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            BlurpStore.reset();
            BlurpStore.configuration.getScriptCompletionHandler().onRestart();
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            BlurpStore.reset();
            BlurpStore.configuration.getScriptCompletionHandler().onTerminate();
        }
    }

    private void doFrame(float delta) {

        synchronized(BlurpStore.blurpifier) {
            if(BlurpStore.blurpifier.getRequestState() == BlurpifyRequestState.Requested) {
                BlurpStore.blurpifier.setRenderState(BlurpifyRenderState.RequestAcknowledged);
                try {
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

        if(BlurpState.debugMode) debugHudRenderer.render();
    }

    private void initialise() {

        batch = new SpriteBatch();
        shapes = new ShapeRenderer();
        debugHudRenderer = new DebugHudRenderer(batch, shapes);

        backgroundStage = new Stage(staticViewport, batch);
        mainStage = new LayerStage(viewport, batch);
        overlayStage = new LayerStage(staticViewport, batch);

        Gdx.input.setInputProcessor(new InputMultiplexer(overlayStage, mainStage, backgroundStage));

        if(Gdx.graphics.getBufferFormat().samples > 0) {
            Gdx.gl.glLineWidth(1.5f);
        } else  {
            Gdx.gl.glLineWidth(1f);
        }

        initialised = true;
        BlurpStore.onLibGdxInitialised();
    }

    private void updateCamera() {

        OrthographicCamera camera = BlurpStore.mainCamera;
        camera.rotate((float) BlurpStore.modelCamera.rotation);
        camera.up.set(0, 1, 0);
        camera.direction.set(0, 0, -1);

        camera.position.set((float) BlurpStore.modelCamera.x, (float) BlurpStore.modelCamera.y, 0);
        camera.rotate((float) BlurpStore.modelCamera.rotation);
        camera.zoom = (float) (1 / BlurpStore.modelCamera.zoom);
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
        BlurpState.debugColour = Convert.toGdxColour(debugColour);
    }

    public void handleSpriteLayer(RuntimeSprite sprite) {

        sprite.remove();
        if(sprite.getLayer() == SpriteLayer.Background) {
            backgroundStage.addActor(sprite);
        } else if(sprite.getLayer() == SpriteLayer.Overlay) {
            overlayStage.addActor(sprite);
        } else {
            mainStage.addActor(sprite);
        }
    }

    public void changeViewport(double width, double height, boolean stretch) {

        changeViewport(viewport, width, height, stretch);
        changeViewport(staticViewport, width, height, stretch);
    }

    private void changeViewport(ScalingViewport viewport, double width, double height, boolean stretch) {

        viewport.setWorldSize((float) width, (float) height);
        viewport.setScaling(stretch ? Scaling.stretch : Scaling.fit);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void reset() {

        backgroundStage.clear();
        mainStage.clear();
        overlayStage.clear();
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
