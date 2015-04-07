package com.bigcustard.blurp.bootstrap;

import java.io.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.scripting.*;
import com.bigcustard.blurp.ui.*;

// TODO: Test
public class BlurpRuntime {

    private BlurpObjectProvider blurpObjectProvider;

    private BlurpRuntime(BlurpObjectProvider blurpObjectProvider) {

        this.blurpObjectProvider = blurpObjectProvider;
    }

    public static BlurpRuntime begin(Viewport viewport) {

        return begin(new BlurpObjectProvider(viewport));
    }

    public static BlurpRuntime begin(BlurpObjectProvider blurpObjectProvider) {

        return new BlurpRuntime(blurpObjectProvider);
    }

    public BlurpScreen getScreen() {

        return blurpObjectProvider.getBlurpScreen();
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

            script.run(blurpObjectProvider.getBlurp(), blurpObjectProvider.getModelScreen(), blurpObjectProvider.getKeyboard(), blurpObjectProvider.getUtils());
            while(true) {
                blurpObjectProvider.getBlurpifier().blurpify();
            }
        }
    }
}
