package com.bigcustard.blurp.bootstrap;

import java.io.*;
import java.lang.reflect.*;
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

// TODO: Test
public class BlurpRuntime {

    private BlurpExceptionHandler exceptionHandler;
    private Runnable scriptRunnable;
    private Thread scriptThread;

    private BlurpRuntime(BlurpConfiguration config, MouseWindowChecker mouseWindowChecker) {

        BlurpStore.initialise(config, mouseWindowChecker, this);

        if(config.isDebugEnabled()) {
            SetDebugModeCommand debugCommand = new SetDebugModeCommand(config.isDebugEnabled(), Colours.LIME_GREEN);
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

    public void startScript(String language, String script, String scriptName) {

        checkScriptRunning("start script");
        startScript(language, new StringReader(script), scriptName);
    }

    public void startScript(String language, Reader scriptReader, String scriptName) {

        checkScriptRunning("start script");
        scriptRunnable = new RunnableWrapper(new ScriptEngineBlurpRunnable(language, scriptReader, scriptName));
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

    public void stop() {

        throw new BlurpTerminatedException();
    }

    public void end() {

        // Note: Use of deprecated stop method is inherently unsafe, however we really do want a hard stop and are going
        // to clear down the runtime so there risk of damaged objects remaining is negligible. As we don;t have control
        // over the code that is running in the thread, this is about the only way to stop it.
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


    // TODO: Need a way to pause / resume / restart / stop the script. Communicate via flags in Repository? Blurpifier states?
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
                BlurpStore.blurp.blurpify();
                script.run();
            } catch (RuntimeException e) {
                if(exceptionHandler != null && !(e instanceof BlurpTerminatedException)) {
                    exceptionHandler.handleException(e);
                }
            }
            BlurpState.scriptComplete = true;
        }
    }
}
