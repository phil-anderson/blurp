package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.util.*;

import static com.badlogic.gdx.utils.Align.center;
import static com.bigcustard.blurp.core.BlurpTerminatedException.CompletionAction.Terminate;

public class DebugHudRenderer {

    private Batch batch;
    private ShapeRenderer shapes;
    private boolean justSwitchedMode = false;

    public DebugHudRenderer(Batch batch, ShapeRenderer shapes) {

        this.batch = batch;
        this.shapes = shapes;
    }

    public void render() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        renderHudButton();
        renderQuitButton();
        if (!MouseState.isInsideWindow()) return;

        if(BlurpState.debugMode) {
            renderDebugActive();
        }
    }

    private void renderHudButton() {
        float size = BlurpStore.staticCamera.viewportWidth / 30;
        float alpha = 0.5f;

        if (mouseWithin(0, 0, size, size) && !justSwitchedMode) {
            if (MouseState.isLeftPressed()) {
                BlurpStore.blurpScreen.enableDebug(!BlurpState.debugMode, BlurpState.debugColour);
                justSwitchedMode = true;
                BlurpStore.timer.after(300, new Runnable() {
                    public void run() {
                        justSwitchedMode = false;
                    }
                });
            }
            alpha = 1f;
        }
        renderGauze(0, 0, size, size, alpha);
        renderCircle(size/3f, size * 0.5f, size * 0.5f);
    }

    private void renderQuitButton() {
        float size = BlurpStore.staticCamera.viewportWidth / 30;
        float alpha = 0.5f;

        if (mouseWithin(size, 0, size, size)) {
            if (MouseState.isLeftPressed()) {
                throw new BlurpTerminatedException(Terminate);
            }
            alpha = 1f;
        }
        renderGauze(size, 0, size, size, alpha);
        renderCross(size/3.5f, size * 1.5f, size * 0.5f);
    }

    private void renderDebugActive() {
        Vector3 mousePosStatic = MouseState.getPosition(ScreenLayer.Overlay);

        shapes.setProjectionMatrix(BlurpStore.staticCamera.combined);
        renderBlackOut();
        renderMouseLines(mousePosStatic);

        float reticuleSize = BlurpStore.mainCamera.viewportWidth / 8;
        shapes.setColor(BlurpState.debugGdxColour());
        renderCameraReticule(reticuleSize, BlurpStore.staticCamera.viewportWidth / 2f, BlurpStore.staticCamera.viewportHeight / 2f);
        renderText(reticuleSize, mousePosStatic);
    }

    private void renderBlackOut() {
        shapes.setColor(0, 0, 0, 0.5f);
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapes.end();
    }

    private void renderGauze(float x, float y, float width, float height, float alpha) {
        shapes.setColor(0.3f, 0.3f, 0.3f, alpha);
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.rect(x, y, width, height);
        shapes.end();
    }

    private boolean mouseWithin(float left, float bottom, float width, float height) {
        Vector3 position = MouseState.getPosition(ScreenLayer.Overlay);
        return position.x < left + width && position.x > left &&
                position.y > bottom && position.y < bottom + height;
    }

    private void renderMouseLines(Vector3 mousePosStatic) {
        shapes.setColor(BlurpState.debugGdxColour());
        shapes.getColor().a = 0.5f;
        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.line(mousePosStatic.x, 0, mousePosStatic.x, BlurpStore.staticCamera.viewportHeight);
        shapes.line(0, mousePosStatic.y, BlurpStore.staticCamera.viewportWidth, mousePosStatic.y);
        shapes.end();
    }

    private void renderCameraReticule(float reticuleSize, float x, float y) {
        float zoom = (float) BlurpStore.modelCamera.zoom;

        shapes.setProjectionMatrix(BlurpStore.mainCamera.combined);
        shapes.begin(ShapeRenderer.ShapeType.Line);
        for(int angle = 0; angle < 360; angle += 5) {
            float sinZoomed = MathUtils.sinDeg(angle) / zoom;
            float cosZoomed = MathUtils.cosDeg(angle) / zoom;
            float innerRadius = angle == 0 ? reticuleSize * 0.75f
                                    : angle % 45 == 0 ? reticuleSize * 0.85f
                                          : angle % 15 == 0 ? reticuleSize * 0.90f
                                                : reticuleSize * 0.95f;
            shapes.line(x + sinZoomed * innerRadius, y + cosZoomed * innerRadius, x + sinZoomed * reticuleSize, y + cosZoomed * reticuleSize);
        }
        shapes.end();

        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.triangle(x, y + reticuleSize * 1.2f / zoom, x - reticuleSize * 0.12f / zoom,
                        y + reticuleSize * 1.05f / zoom, x + reticuleSize * 0.12f / zoom,
                        y + reticuleSize * 1.05f / zoom);

        shapes.end();
    }

    private void renderCross(float size, float x, float y) {//        shapes.setProjectionMatrix(BlurpStore.staticCamera.combined);
        shapes.setColor(Color.SCARLET);
        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.line(x - size, y - size, x + size, y + size);
        shapes.line(x + size, y - size, x - size, y + size);
        shapes.end();
    }

    private void renderCircle(float size, float x, float y) {//        shapes.setProjectionMatrix(BlurpStore.staticCamera.combined);
        shapes.setColor(Color.GREEN);
        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.circle(x, y, size);
        shapes.end();
    }

    private void renderText(float reticuleSize, Vector3 mousePosStatic) {

        BitmapFont font = BlurpStore.defaultFont.getFont();
        font.getData().setScale(reticuleSize * 0.003f);
        font.setColor(BlurpState.debugGdxColour());

        String zoomString = String.format("%.2f", (float) BlurpStore.modelCamera.zoom);
        String angleString = String.format("%.1f", 360 - BlurpStore.modelCamera.angle % 360) + "°";
        String staticMouseString = String.format("x:%.1f  y:%.1f", mousePosStatic.x, mousePosStatic.y);

        batch.begin(); // Assumes batch was left in "static camera" mode

        font.draw(batch, angleString, BlurpStore.mainViewport.getScreenWidth() / 2 - 100,
                BlurpStore.mainViewport.getScreenHeight() / 2 + font.getCapHeight(),
                           200, center, false);
        font.draw(batch, "x" + zoomString, BlurpStore.mainViewport.getScreenWidth() / 2 - 100,
                BlurpStore.mainViewport.getScreenHeight() / 2,
                           200, center, false);
        font.draw(batch, staticMouseString, BlurpStore.mainViewport.getScreenWidth() / 2 - 100,
                BlurpStore.mainViewport.getScreenHeight() / 2 - reticuleSize * 1.25f,
                           200, center, false);

//        Vector3 mousePosMain = MouseState.getPosition(ScreenLayer.Main);
//        if(!mousePosMain.equals(mousePosStatic)) {
//            String mainMouseString = String.format("(x:%.1f  y:%.1f)", mousePosMain.x, mousePosMain.y);
//            font.draw(batch, mainMouseString, BlurpStore.mainCamera.viewportWidth / 2 - 100,
//                               BlurpStore.mainCamera.viewportHeight / 2 - reticuleSize * 1.6f,
//                               200, Align.center, false);
//        }

        batch.end();
    }
}
