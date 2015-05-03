package com.bigcustard.blurp.bootstrap;

import java.io.*;
import com.badlogic.gdx.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.scripting.*;
import com.bigcustard.blurp.ui.*;

// TODO: Test
public class BlurpRuntime {

    private BlurpObjectProvider blurpObjectProvider;
    private BlurpExceptionHandler exceptionHandler;

    private BlurpRuntime(BlurpConfiguration config) {

        this.blurpObjectProvider = new BlurpObjectProvider(config);

        if(config.isDebugEnabled()) {
            SetDebugModeCommand debugCommand = new SetDebugModeCommand(config.isDebugEnabled(), Colours.LIME_GREEN);
            blurpObjectProvider.getRuntimeRepository().registerCommand(debugCommand);
        }
    }

    public static BlurpRuntime begin(BlurpConfiguration config) {

        return new BlurpRuntime(config);
    }

    public void onException(BlurpExceptionHandler exceptionHandler) {

        this.exceptionHandler = exceptionHandler;
    }

    public Screen getScreen() {

        return blurpObjectProvider.getBlurpScreen();
    }

    public void onRenderEvent(RenderListener listener) {

        blurpObjectProvider.getBlurpScreen().onRenderEvent(listener);
    }

    public void start(String language, String script, String scriptName) {

        start(language, new StringReader(script), scriptName);
    }

    // TODO: DANGER! - Pretty sure this won't work on Android.
    public void start(String language, Reader scriptReader, String scriptName) {

        start(new ScriptEngineBlurpRunnable(language, scriptReader, scriptName));
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

            try {
                script.run(blurpObjectProvider.getBlurp(),
                           blurpObjectProvider.getModelScreen(),
                           blurpObjectProvider.getKeyboard(),
                           blurpObjectProvider.getUtils(),
                           blurpObjectProvider.getEffects());

            } catch (RuntimeException e) {
                if(exceptionHandler != null) {
                    exceptionHandler.handleException(e);
                }
            }
        }
    }
}
