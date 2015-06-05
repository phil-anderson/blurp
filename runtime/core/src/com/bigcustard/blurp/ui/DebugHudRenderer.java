package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.util.*;

public class DebugHudRenderer {

    private Batch batch;
    private ShapeRenderer shapes;

    public DebugHudRenderer(Batch batch, ShapeRenderer shapes) {

        this.batch = batch;
        this.shapes = shapes;
    }

    public void render() {

        if(!MouseState.isInsideWindow()) return;

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapes.setProjectionMatrix(BlurpStore.staticCamera.combined);
        renderBlackOut();
        renderMouseLines();

        float reticuleSize = BlurpStore.mainCamera.viewportWidth / 8;
        renderCameraReticule(reticuleSize);
        renderText(reticuleSize);
    }

    private void renderBlackOut() {

        shapes.setColor(0, 0, 0, 0.5f);
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.rect(0, 0, BlurpStore.staticCamera.viewportWidth, BlurpStore.staticCamera.viewportHeight);
        shapes.end();
    }

    private void renderMouseLines() {

        Vector3 mousePosStatic = MouseState.getPosition(SpriteLayer.Overlay);
        shapes.setColor(BlurpState.debugGdxColour());
        shapes.getColor().a = 0.5f;
        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.line(mousePosStatic.x, 0, mousePosStatic.x, BlurpStore.staticCamera.viewportHeight);
        shapes.line(0, mousePosStatic.y, BlurpStore.staticCamera.viewportWidth, mousePosStatic.y);
        shapes.end();
    }

    private void renderCameraReticule(float reticuleSize) {

        float zoom = (float) BlurpStore.modelCamera.zoom;
        Vector3 center = Convert.screenToMainLayer(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        shapes.setProjectionMatrix(BlurpStore.mainCamera.combined);
        shapes.setColor(BlurpState.debugGdxColour());
        shapes.begin(ShapeRenderer.ShapeType.Line);
        for(int angle = 0; angle < 360; angle += 5) {
            float sinZoomed = MathUtils.sinDeg(angle) / zoom;
            float cosZoomed = MathUtils.cosDeg(angle) / zoom;
            float innerRadius = angle == 0 ? reticuleSize * 0.75f
                                    : angle % 45 == 0 ? reticuleSize * 0.85f
                                          : angle % 15 == 0 ? reticuleSize * 0.90f
                                                : reticuleSize * 0.95f;
            shapes.line(center.x + sinZoomed * innerRadius, center.y + cosZoomed * innerRadius, center.x + sinZoomed * reticuleSize, center.y + cosZoomed * reticuleSize);
        }
        shapes.end();

        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.triangle(center.x, center.y + reticuleSize * 1.2f / zoom, center.x - reticuleSize * 0.12f / zoom,
                        center.y + reticuleSize * 1.05f / zoom, center.x + reticuleSize * 0.12f / zoom,
                        center.y + reticuleSize * 1.05f / zoom);

        shapes.end();

    }

    private void renderText(float reticuleSize) {

        batch.begin(); // Assumes batch was left in "static camera" mode
        BitmapFont font = BlurpStore.defaultFont.getFont();
        font.setScale(reticuleSize * 0.003f);
        font.setColor(BlurpState.debugGdxColour());

        Vector3 mousePosMain = MouseState.getPosition(SpriteLayer.Main);

        String zoomString = String.format("%.2f", (float) BlurpStore.modelCamera.zoom);
        String angleString = String.format("%.1f", 360 - BlurpStore.modelCamera.rotation % 360) + (char) 127;
        String mouseString = String.format("x:%.1f  y:%.1f", mousePosMain.x, mousePosMain.y);

        font.drawMultiLine(batch, angleString, BlurpStore.mainCamera.viewportWidth / 2 - 100,
                           BlurpStore.mainCamera.viewportHeight / 2 + font.getCapHeight(),
                           200, BitmapFont.HAlignment.CENTER);
        font.drawMultiLine(batch, "x" + zoomString, BlurpStore.mainCamera.viewportWidth / 2 - 100,
                           BlurpStore.mainCamera.viewportHeight / 2,
                           200, BitmapFont.HAlignment.CENTER);
        font.drawMultiLine(batch, mouseString, BlurpStore.mainCamera.viewportWidth / 2 - 100,
                           BlurpStore.mainCamera.viewportHeight / 2 - reticuleSize * 1.25f,
                           200, BitmapFont.HAlignment.CENTER);
        batch.end();
    }
}
