package com.bigcustard.blurp.bootstrap;

import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.ui.*;

public class BlurpRuntime {

    private SingletonFactory singletonFactory;

    public BlurpRuntime() { }

    public static BlurpRuntime begin(Viewport viewport) {

        return begin(new SingletonFactory(viewport));
    }

    public static BlurpRuntime begin(SingletonFactory singletonFactory) {

        singletonFactory.initialiseSingletons();
        return new BlurpRuntime();
    }

    public BlurpScreen getScreen() {

        return RSS.getBlurpScreen();
    }

    public void start(IBlurpRunnable script) {

        Thread scriptThread = new Thread(new ScriptRunnable(script));
        scriptThread.start();
    }

    // TODO: Implement this properly
    public void end() {

        RSS.dispose();
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

            script.run(RSS.getBlurp(), RSS.getCanvas(), RSS.getKeyboard());
            while(true) {
                RSS.getBlurpifier().blurpify();
            }
        }
    }
}
