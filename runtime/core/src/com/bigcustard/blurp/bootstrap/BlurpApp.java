package com.bigcustard.blurp.bootstrap;

import java.io.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.java.*;
import com.bigcustard.blurp.ui.*;

// TODO: Tidy this up a bit. Don't like having Scripts AND Classes  - feels like it should be split in two.
public class BlurpApp extends Game {

    private final ScalingViewport viewport;
    private final MouseWindowChecker mouseWindowChecker;
    private String scriptName;
    private String language;
    private Class<? extends BlurpJavaProgram> javaClass;

    public BlurpApp(String language, String scriptName, ScalingViewport viewport, MouseWindowChecker mouseWindowChecker) {

        this.language = language;
        if(language.equalsIgnoreCase("java")) {
            try {
                this.javaClass = (Class<? extends BlurpJavaProgram>) Class.forName(scriptName);
            } catch(Exception e) {
                throw new BlurpException("Error finding BlurpJavaProgram subclass " + scriptName, e);
            }
        } else {
            this.scriptName = scriptName;
        }
        this.viewport = viewport;
        this.mouseWindowChecker = mouseWindowChecker;
    }

    // TODO - Need to override other lifecycle methods
    @Override
    public void create() {

        BlurpConfiguration config = new BlurpConfiguration(viewport);
        BlurpRuntime blurpRuntime = BlurpRuntime.begin(config, mouseWindowChecker);

        blurpRuntime.onException(new BlurpExceptionHandler() {
            @Override
            public void handleException(RuntimeException e) {
                e.printStackTrace();
                System.exit(1);
            }
        });
        if(javaClass != null) {
            blurpRuntime.startClass(javaClass);
        } else {
            // TODO: Think about this.
            try {
                blurpRuntime.startScript(language, new FileReader(scriptName), scriptName);
            } catch(FileNotFoundException e) {
                throw new BlurpException("Error finding script file " + scriptName, e);
            }
        }
        setScreen(BlurpStore.blurpScreen);
    }
}
