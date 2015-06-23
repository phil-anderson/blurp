package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.util.*;

import static com.bigcustard.blurp.core.BlurpTerminatedException.CompletionAction.*;

public class ScriptCompleteOverlay {

    private boolean errorHandled;

    private SimpleButton errorButton;
    private SimpleButton restartButton;
    private SimpleButton exitButton;

    public ScriptCompleteOverlay() {

        errorButton = new SimpleButton("error-icon.png", Colours.Red);
        restartButton = new SimpleButton("restart-icon.png", Colours.White);
        exitButton = new SimpleButton("exit-icon.png", Colours.White);
    }

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

            batch.begin();
            BlurpStore.runtimeConsole.render(batch);
            batch.end();
        }

        float size = BlurpStore.staticCamera.viewportHeight / 10;

        shapes.setColor(0, 0, 0f, 1f);
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.rect(0, 0, BlurpStore.staticCamera.viewportWidth, size * 1.2f);
        shapes.end();

        shapes.setColor(Color.GRAY);
        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.line(0, size * 1.2f, BlurpStore.staticCamera.viewportWidth, size * 1.2f);
        shapes.end();

        float xPos = 400 - size; // Position for two buttons

        batch.setProjectionMatrix(BlurpStore.staticCamera.combined);
        batch.begin();
        if(BlurpState.error) {
            xPos -= size / 2; // Position for three buttons
            if(!errorHandled) {
                BlurpStore.runtimeConsole.clear();
                BlurpStore.runtimeConsole.print(Exceptions.getConcatenatedMessage(BlurpState.exception), Colours.LightGrey, 0.75);
                errorHandled = true;
            }
            errorButton.setPosition(xPos, size * 0.1f, size);
            errorButton.render(batch);
            xPos += size;
        }

        restartButton.setPosition(xPos, size * 0.1f, size);
        xPos += size;
        exitButton.setPosition(xPos, size * 0.1f, size);

        restartButton.render(batch);
        exitButton.render(batch);
        batch.end();

        if(errorButton.wasClicked()) {
            BlurpStore.runtimeConsole.print(Exceptions.getStackTraceString(BlurpState.exception), Colours.LightGrey, 1);
        } else if(restartButton.wasClicked()) {
            BlurpStore.configuration.getScriptCompletionHandler().onRestart();
        } else if(exitButton.wasClicked()) {
            BlurpStore.configuration.getScriptCompletionHandler().onTerminate();
        }
    }

    public void dispose() {

        reset();
        errorButton.dispose();
        restartButton.dispose();
        exitButton.dispose();
    }

    public void reset() {

        errorHandled = false;
    }
}
