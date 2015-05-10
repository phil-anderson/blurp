package com.bigcustard.blurp.bootstrap;

import java.io.*;
import java.lang.reflect.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.scripting.*;
import com.bigcustard.blurp.ui.*;
import com.bigcustard.blurp.util.*;

// TODO: Test
public class BlurpRuntime {

    private BlurpExceptionHandler exceptionHandler;
    private Thread scriptThread;

    private BlurpRuntime(BlurpConfiguration config) {

        BlurpStore.initialise(config);

        if(config.isDebugEnabled()) {
            SetDebugModeCommand debugCommand = new SetDebugModeCommand(config.isDebugEnabled(), Colours.LIME_GREEN);
            BlurpStore.runtimeRepository.registerCommand(debugCommand);
        }
    }

    public static BlurpRuntime begin(BlurpConfiguration config) {

        initLibGdxColors();
        return new BlurpRuntime(config);
    }

    public void onException(BlurpExceptionHandler exceptionHandler) {

        this.exceptionHandler = exceptionHandler;
    }

    public void onRenderEvent(RenderListener listener) {

        BlurpStore.blurpScreen.onRenderEvent(listener);
    }

    public void start(String language, String script, String scriptName) {

        start(language, new StringReader(script), scriptName);
    }

    // TODO: DANGER! - Pretty sure this won't work on Android.
    public void start(String language, Reader scriptReader, String scriptName) {

        start(new ScriptEngineBlurpRunnable(language, scriptReader, scriptName));
    }

    public void start(BlurpRunnable blurpRunnable) {

        scriptThread = new Thread(new RunnableWrapper(blurpRunnable));
        scriptThread.start();
    }

    // TODO: Implement this properly
    public void end() {


        // TODO: Add explanation comment
        scriptThread.stop();
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
                script.run(BlurpStore.blurp,
                           BlurpStore.modelScreen,
                           BlurpStore.modelCamera,
                           BlurpStore.effects,
                           new KeyboardImpl(),
                           new Utils());

            } catch (RuntimeException e) {
                if(exceptionHandler != null) {
                    exceptionHandler.handleException(e);
                }
            }
        }
    }
}
