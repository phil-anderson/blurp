package com.bigcustard.blurp.bootstrap;

import java.io.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.scripting.*;
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

    public void start(String language, String script) {

        start(language, new StringReader(script));
    }

    // TODO: DANGER! - Pretty sure this won't work on Android.
    public void start(String language, Reader scriptReader) {

        start(new ScriptEngineBlurpRunnable(language, scriptReader));
    }

    public void start(BlurpRunnable blurpRunnable) {

        Thread scriptThread = new Thread(new RunnableWrapper(blurpRunnable));
        scriptThread.start();
    }

    // TODO: Implement this properly
    public void end() {

        RSS.dispose();
    }

    // TODO: Need a way to pause / resume / restart / stop the script. Communicate via flags in Repository? Blurpifier states?
    //       Note - When paused, the Blurpifier will still have to allow renders to continue so that input gestures
    //              (e.g. to resume) get picked up.
    private class RunnableWrapper implements Runnable {

        private BlurpRunnable script;

        private RunnableWrapper(BlurpRunnable script) {

            this.script = script;
        }

        @Override
        public void run() {

            script.run(RSS.getBlurp(), RSS.getCanvas(), RSS.getKeyboard(), RSS.getUtils());
            while(true) {
                RSS.getBlurpifier().blurpify();
            }
        }
    }
}
