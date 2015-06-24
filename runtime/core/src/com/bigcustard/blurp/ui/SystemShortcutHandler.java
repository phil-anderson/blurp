package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;
import com.bigcustard.blurp.core.*;

public class SystemShortcutHandler {

    public void check() {

        if(!BlurpState.scriptComplete) {
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) || Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {
                if(Gdx.input.isKeyJustPressed(Input.Keys.C)) {
                    Gdx.app.getClipboard().setContents(BlurpStore.runtimeConsole.toString());
                } else if(Gdx.input.isKeyJustPressed(Input.Keys.L)) {
                    BlurpStore.runtimeConsole.clear();
                } else if(Gdx.input.isKeyJustPressed(Input.Keys.D)) {
                    BlurpStore.blurpScreen.enableDebug(!BlurpState.debugMode, BlurpState.debugColour);
                } else if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
                    BlurpState.togglePause();
                } else if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
                    if(BlurpState.paused) BlurpState.resume();
                    BlurpStore.runtime.stop();
                } else if(Gdx.input.isKeyJustPressed(Input.Keys.R) || Gdx.input.isKeyJustPressed(Input.Keys.F5)) {
                    if(BlurpState.paused) BlurpState.resume();
                    BlurpStore.runtime.restart();
                } else if(Gdx.input.isKeyJustPressed(Input.Keys.Q) || Gdx.input.isKeyJustPressed(Input.Keys.F4)) {
                    if(BlurpState.paused) BlurpState.resume();
                    BlurpStore.runtime.terminate();
                }
            }
        }
    }
}
