package com.bigcustard.blurp.desktop;

import java.io.*;
import com.badlogic.gdx.*;
import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.java.*;
import com.bigcustard.blurp.ui.*;

// TODO: Tidy this up a bit. Don't like having Scripts AND Classes  - feels like it should be split in two.
public class BlurpApp extends Game {

    private final double viewportWidth, viewportHeight;
    private final MouseWindowChecker mouseWindowChecker;
    private String scriptName;
    private String language;
    private Class<? extends BlurpJavaProgram> javaClass;

    public BlurpApp(String language, String scriptName, double viewportWidth, double viewportHeight, MouseWindowChecker mouseWindowChecker) {

        this.language = language;
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
        if(language.equalsIgnoreCase("java")) {
            try {
                this.javaClass = (Class<? extends BlurpJavaProgram>) Class.forName(scriptName);
            } catch(Exception e) {
                throw new BlurpException("Error finding BlurpJavaProgram subclass " + scriptName, e);
            }
        } else {
            this.scriptName = scriptName;
        }
        this.mouseWindowChecker = mouseWindowChecker;
    }

    // TODO - Need to override other lifecycle methods
    @Override
    public void create() {

        BlurpState.windowTitle = "Blurp: " + (javaClass != null ? javaClass.getSimpleName() : scriptName);
        Gdx.graphics.setTitle(BlurpState.windowTitle);
        BlurpConfiguration config = new BlurpConfiguration(viewportWidth, viewportHeight);
        BlurpRuntime blurpRuntime = BlurpRuntime.begin(config, mouseWindowChecker);

        blurpRuntime.onException(new BlurpExceptionHandler() {
            @Override
            public void handleException(RuntimeException e) {
                e.printStackTrace();
            }
        });
        if(javaClass != null) {
            blurpRuntime.startClass(javaClass);
        } else {
            String parent = new File(scriptName).getAbsoluteFile().getParent();
            if(parent != null) {
                config.setContentRoot(parent);
            }
            blurpRuntime.startScript(language, scriptName);
        }
        setScreen(BlurpStore.blurpScreen);
    }
}
