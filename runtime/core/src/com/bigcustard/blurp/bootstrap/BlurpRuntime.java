package com.bigcustard.blurp.bootstrap;

import java.lang.System;
import java.lang.reflect.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.*;
import com.bigcustard.blurp.bootstrap.languages.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.ui.*;
import com.bigcustard.blurp.util.*;

// TODO: Test
public class BlurpRuntime {

    private BlurpExceptionHandler exceptionHandler;
    private Runnable runnable;
    private Thread scriptThread;

    private BlurpRuntime(BlurpConfiguration config, MouseWindowChecker mouseWindowChecker) {

        BlurpStore.initialise(config, mouseWindowChecker, this);

        if(config.isDebugEnabled()) {
            SetDebugModeCommand debugCommand = new SetDebugModeCommand(config.isDebugEnabled(), Colours.LimeGreen);
            BlurpStore.runtimeRepository.registerCommand(debugCommand);
        }
    }

    public static BlurpRuntime begin(BlurpConfiguration config, MouseWindowChecker mouseWindowChecker) {

        initLibGdxColors();
        return new BlurpRuntime(config, mouseWindowChecker);
    }

    public void onException(BlurpExceptionHandler exceptionHandler) {

        this.exceptionHandler = exceptionHandler;
    }

    public void onRenderEvent(RenderListener listener) {

        BlurpStore.blurpScreen.onRenderEvent(listener);
    }

    public void start(SupportedLanguage language, String filename) {

        checkScriptRunning("start script");

        Runner runner = language.getRunner();
        runner.prepare(filename);
        runnable = new RunnableWrapper(runner);
        startThread();
    }

    public void pause() {

        BlurpState.pause();
    }

    public void reset() {


        BlurpStore.reset();
    }

    public void end() {

        BlurpStore.dispose();
    }

    public void startThread() {

        checkScriptRunning("start script thread");
        scriptThread = new Thread(runnable, "ScriptThread");
        scriptThread.start();
    }

    private static void initLibGdxColors() {

        // Populate the LibGdx Colors map with the Blurp colours so things like TextMarkup will use them.
        ObjectMap<String,Color> colorMap = Colors.getColors();
        colorMap.clear();

        try {
            for(Field field : Colours.class.getFields()) {
                Color libGdxColor = Convert.toGdxColour((Colour) field.get(null));
                colorMap.put(field.getName(), libGdxColor);
                colorMap.put(Convert.toCamelCase(field.getName()), libGdxColor);
            }
        } catch(IllegalAccessException e) {
            throw new IllegalStateException("Colours class contains inaccessible, or non-constant fields");
        }
    }

    private void checkScriptRunning(String action) {

        if (BlurpState.scriptComplete) {

            throw new BlurpException("Can't " + action + " while a script thread is still running");
        }
    }

    private class RunnableWrapper implements Runnable {

        private Runnable script;

        private RunnableWrapper(Runnable script) {

            this.script = script;
        }

        @Override
        public void run() {

            try {
                // Initial blurpify to flush any defaults set in the model.
                BlurpStore.blurpifier.blurpify();
                BlurpState.startTime = System.currentTimeMillis();
                script.run();
                BlurpStore.blurpifier.blurpify(); // Allows for non-looping scripts e.g. one-liner hello world
            } catch (RuntimeException e) {
                BlurpState.exception = Exceptions.exposeUserTemination(e);
                if(exceptionHandler != null && !(BlurpState.exception instanceof BlurpTerminatedException)) {
                    BlurpState.error = true;
                    exceptionHandler.handleException(BlurpState.exception);
                }
            }

            BlurpStore.runtimeRepository.disposeAudio();
            BlurpStore.scriptCompleteOverlay.initialise();
            BlurpState.scriptComplete = true;
        }
    }
}
