package com.bigcustard.blurp.core;

import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.ui.*;

public class BlurpRuntime {

    private BlurpRuntime() { }

    public static BlurpRuntime begin(Viewport viewport) {

        SF.instantiateSingletons(viewport);
        return new BlurpRuntime();
    }

    public BlurpScreen getScreen() {

        return SF.getBlurpScreen();
    }

    public void start(IBlurpRunnable script) {

        Thread scriptThread = new Thread(new ScriptRunnable(script));
        scriptThread.start();
    }

    // TODO: Implement this properly
    public void end() {

        SF.dispose();
    }

    // TODO: Need a way to pause / resume / restart / stop the script. Communicate via flags in Repository? Blurpifier states?
    //       Note - When paused, the Blurpifier will still have to allow renders to continue so that input gestures
    //              (e.g. to resume) get picked up.
    private class ScriptRunnable implements Runnable {

        private IBlurpRunnable script;

        private ScriptRunnable(IBlurpRunnable script) {

            this.script = script;
        }

        @Override
        public void run() {

            script.run(SF.getBlurp(), SF.getCanvas(), SF.getKeyboard());
            while(true) {
                SF.getBlurpifier().blurpify();
            }
        }
    }
}
