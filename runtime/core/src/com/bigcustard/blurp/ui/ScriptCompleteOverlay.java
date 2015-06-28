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

    private SimpleButton successButton;
    private SimpleButton errorButton;
    private SimpleButton restartButton;
    private SimpleButton exitButton;

    private SimpleButton leftHandButton;
    private float alpha = 0;
    private float size;
    private int programDuration;

    public ScriptCompleteOverlay() {

        successButton = new SimpleButton("success-icon.png", Colours.Lime);
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

        if(alpha < 0.5) alpha += 0.01;
        shapes.setProjectionMatrix(BlurpStore.staticCamera.combined);
        shapes.setColor(0, 0, 0, alpha);
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.rect(0, 0, BlurpStore.staticCamera.viewportWidth, BlurpStore.staticCamera.viewportHeight);
        shapes.end();

        batch.begin();
        BlurpStore.runtimeConsole.render(batch);
        batch.end();

        shapes.setColor(0, 0, 0f, 1f);
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.rect(0, 0, BlurpStore.staticCamera.viewportWidth, size * 1.2f);
        shapes.end();

        shapes.setColor(Color.GRAY);
        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.line(0, size * 1.2f, BlurpStore.staticCamera.viewportWidth, size * 1.2f);
        shapes.end();

        batch.setProjectionMatrix(BlurpStore.staticCamera.combined);
        batch.begin();
        leftHandButton.render(batch);
        restartButton.render(batch);
        exitButton.render(batch);
        batch.end();

        if(leftHandButton.wasClicked()) {
            if(BlurpState.error) {
                BlurpStore.runtimeConsole.print(Exceptions.getStackTraceString(BlurpState.exception), Colours.LightGrey, 1);
            } else {
                String overview = String.format("\nTime taken: %dms\nFrames Rendered: %d\nAverage FPS: %.2f",
                              programDuration, BlurpState.numFrames, BlurpState.numFrames / (float) programDuration * 1000);
                BlurpStore.runtimeConsole.print(overview, Colours.LightGrey, 1);
            }
        } else if(restartButton.wasClicked()) {
            BlurpStore.configuration.getScriptCompletionHandler().onRestart();
        } else if(exitButton.wasClicked()) {
            BlurpStore.configuration.getScriptCompletionHandler().onTerminate();
        }
    }

    public void initialise() {

        programDuration = (int) (System.currentTimeMillis() - BlurpState.startTime);
        alpha = 0;
        if(BlurpState.error) {
            BlurpStore.runtimeConsole.print(Exceptions.getConcatenatedMessage(BlurpState.exception), Colours.LightGrey, 0.75);
            leftHandButton = errorButton;
        } else {
            BlurpStore.runtimeConsole.print("Program Complete\n", Colours.LightGrey, 1);
            leftHandButton = successButton;
        }

        size = BlurpStore.staticCamera.viewportHeight / 20;
        float xPos = 400 - size * 1.5f;

        successButton.setPosition(xPos, size * 0.1f, size);
        errorButton.setPosition(xPos, size * 0.1f, size);
        xPos += size;

        restartButton.setPosition(xPos, size * 0.1f, size);
        xPos += size;
        exitButton.setPosition(xPos, size * 0.1f, size);
    }

    public void dispose() {

        errorButton.dispose();
        restartButton.dispose();
        exitButton.dispose();
    }
}
