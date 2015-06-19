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
                    BlurpStore.runtime.stop();
                } else if(Gdx.input.isKeyJustPressed(Input.Keys.R) || Gdx.input.isKeyJustPressed(Input.Keys.F5)) {
                    BlurpStore.runtime.restart();
                } else if(Gdx.input.isKeyJustPressed(Input.Keys.Q) || Gdx.input.isKeyJustPressed(Input.Keys.F4)) {
                    BlurpStore.runtime.terminate();
                }
            }
        }
    }


}
