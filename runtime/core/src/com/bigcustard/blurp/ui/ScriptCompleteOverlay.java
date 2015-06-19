package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.util.*;

import static com.bigcustard.blurp.core.BlurpTerminatedException.CompletionAction.Restart;
import static com.bigcustard.blurp.core.BlurpTerminatedException.CompletionAction.Terminate;

public class ScriptCompleteOverlay {

    private boolean errorTriggered;

    public void render(Batch batch, ShapeRenderer shapes) {

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        // Was it stopped with a system action?
        if(BlurpState.exception instanceof BlurpTerminatedException) {
            BlurpTerminatedException exception = (BlurpTerminatedException) BlurpState.exception;
            if(exception.getAction() == Restart) {
                BlurpStore.configuration.getScriptCompletionHandler().onRestart();
                return;
            } else if(exception.getAction() == Terminate) {
                BlurpStore.configuration.getScriptCompletionHandler().onTerminate();
                return;
            }
        }

        if(BlurpState.error) {
            shapes.setProjectionMatrix(BlurpStore.staticCamera.combined);
            shapes.setColor(0, 0, 0, 0.5f);
            shapes.begin(ShapeRenderer.ShapeType.Filled);
            shapes.rect(0, 0, BlurpStore.staticCamera.viewportWidth, BlurpStore.staticCamera.viewportHeight);
            shapes.end();
        }

        float textHeight = BlurpStore.staticCamera.viewportHeight / 20;

        shapes.setProjectionMatrix(BlurpStore.staticCamera.combined);

        shapes.setColor(0, 0, 0.25f, 1f);
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
        font.setMarkupEnabled(true);
        if(BlurpState.error) {
            if(!errorTriggered) {
                BlurpStore.runtimeConsole.clear();
                BlurpStore.runtimeConsole.print(Exceptions.getConcatenatedMessage(BlurpState.exception), Colours.White, 1);
                errorTriggered = true;
            }
            font.drawWrapped(batch, "[Red]An Error Occurred[] - Press SPACE to restart, ESC to exit", 0, textHeight, BlurpStore.staticCamera.viewportWidth, BitmapFont.HAlignment.CENTER);
            BlurpStore.runtimeConsole.render(batch);
        } else {
            font.drawWrapped(batch, "Program Complete - Press SPACE to restart, ESC to exit", 0, textHeight, BlurpStore.staticCamera.viewportWidth, BitmapFont.HAlignment.CENTER);
        }
        batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            BlurpStore.configuration.getScriptCompletionHandler().onRestart();
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            BlurpStore.configuration.getScriptCompletionHandler().onTerminate();
        }
    }

    public void reset() {

        errorTriggered = false;
    }
}
