package com.bigcustard.blurp.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.model.*;

public class BlurpRuntime {

    private BlurpRuntime(Viewport viewport) { }

    public static BlurpRuntime begin(Viewport viewport) {

        SF.instantiateSingletons(viewport);
        return new BlurpRuntime(viewport);
    }

    public Screen getScreen() {

        return SF.getBlurpScreen();
    }

    public void start(BlurpMain script) {

        Thread scriptThread = new Thread(new ScriptRunnable(script));
        scriptThread.start();
    }

    // TODO: Implement this properly
    public void stop() {

        SF.dispose();
    }

    // TODO: Need a way to pause / resume / restart / stop the script. Communicate via flags in Repository? Blurpifier states?
    //       Note - When paused, the Blurpifier will still have to allow renders to continue so that input gestures
    //              (e.g. to resume) get picked up.
    private class ScriptRunnable implements Runnable {

        private BlurpMain script;

        private ScriptRunnable(BlurpMain script) {

            this.script = script;
        }

        @Override
        public void run() {

            script.run();
            while(true) {
                SF.getBlurpifier().blurpify();
//                Sleep.forOneMs();
            }
        }
    }
}
