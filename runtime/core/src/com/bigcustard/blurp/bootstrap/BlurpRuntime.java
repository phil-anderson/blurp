package com.bigcustard.blurp.bootstrap;

import java.lang.reflect.*;
import java.lang.System;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.java.*;
import com.bigcustard.blurp.model.java.bootstrap.*;
import com.bigcustard.blurp.scripting.*;
import com.bigcustard.blurp.ui.*;
import com.bigcustard.blurp.util.*;

import static com.bigcustard.blurp.core.BlurpTerminatedException.CompletionAction.*;

// TODO: Test
public class BlurpRuntime {

    private BlurpExceptionHandler exceptionHandler;
    private Runnable scriptRunnable;
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

    public void startScript(String language, String scriptFilename) {

        checkScriptRunning("start script");
        scriptRunnable = new RunnableWrapper(new ScriptEngineBlurpRunnable(language, scriptFilename));
        startThread();
    }

    public void startClass(Class<? extends BlurpJavaProgram> javaClass) {

        checkScriptRunning("start class");
        try {
            JavaBootstrapHolder.initialise(new JavaBootstrapImpl());
            BlurpJavaProgram blurpJavaProgram = javaClass.newInstance();
            scriptRunnable = new RunnableWrapper(blurpJavaProgram);
        } catch(Exception e) {
            throw new BlurpException("Error instantiating BlurpJavaProgram " + javaClass.getName(), e);
        }
        startThread();
    }

    public void pause() {

        BlurpState.pause();
    }

    public void resume() {

        BlurpState.resume();
    }

    public void stop() {

        throw new BlurpTerminatedException(Normal);
    }

    public void terminate() {

        throw new BlurpTerminatedException(Terminate);
    }

    public void restart() {

        throw new BlurpTerminatedException(Restart);
    }

    public void end() {

        BlurpStore.dispose();
    }

    public void startThread() {

        checkScriptRunning("start script thread");
        scriptThread = new Thread(scriptRunnable);
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

    // TODO: Need a way to pause / resume the script
    //       Note - When paused, the Blurpifier will still have to allow renders to continue so that input gestures
    //              (e.g. to resume) get picked up.
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
            } catch (RuntimeException e) {
                BlurpState.exception = e;
                if(exceptionHandler != null && !(e instanceof BlurpTerminatedException)) {
                    BlurpState.error = true;
                    exceptionHandler.handleException(e);
                }
            }

            BlurpStore.scriptCompleteOverlay.initialise();
            BlurpState.scriptComplete = true;
        }
    }
}
