package com.bigcustard.blurp.bootstrap;

import java.io.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.scripting.*;

public class BlurpApp extends Game {

    private BlurpRunnable scriptRunnable;
    private Viewport viewport;

    public BlurpApp(String language, String scriptName, Viewport viewport) {

        scriptRunnable = getRunnable(language, scriptName);
        this.viewport = viewport;
    }

    // TODO - Need to override other lifecycle methods
    @Override
    public void create() {

        BlurpRuntime blurpRuntime = BlurpRuntime.begin(viewport);
        blurpRuntime.start(scriptRunnable);
        setScreen(blurpRuntime.getScreen());
    }

    private BlurpRunnable getRunnable(String language, String scriptName) {

        BlurpRunnable blurpRunnable;
        if(language.equalsIgnoreCase("java")) {
            return instantiateJavaClass(scriptName);
        } else {
            return createScriptRunnable(language, scriptName);
        }
    }

    private BlurpRunnable instantiateJavaClass(String className) {
        try {
            Class<BlurpRunnable> scriptClass = (Class<BlurpRunnable>) Class.forName(className);
            return scriptClass.newInstance();
        } catch(Exception e) {
            throw new BlurpException("Error instantiating " + className + " as an instance of BlurpRunnable");
        }
    }

    private BlurpRunnable createScriptRunnable(String language, String scriptName) {

        // TODO: DANGER! Pretty sure this won't work on Android
        try {
            return new ScriptEngineBlurpRunnable(language, new FileReader(scriptName));
        } catch(FileNotFoundException e) {
            throw new BlurpException("Error loading script file " + scriptName);
        }
    }
}