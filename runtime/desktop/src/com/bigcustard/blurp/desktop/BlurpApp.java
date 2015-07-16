package com.bigcustard.blurp.desktop;

import java.io.*;
import com.badlogic.gdx.*;
import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.bootstrap.languages.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.ui.*;

public class BlurpApp extends Game {

    private final double viewportWidth, viewportHeight;
    private final MouseWindowChecker mouseWindowChecker;
    private String scriptName;
    private SupportedLanguage language;

    public BlurpApp(SupportedLanguage language, String scriptName, double viewportWidth, double viewportHeight, MouseWindowChecker mouseWindowChecker) {

        this.language = language;
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
        this.scriptName = scriptName;
        this.mouseWindowChecker = mouseWindowChecker;
    }

    // TODO - Need to override other lifecycle methods
    @Override
    public void create() {

        BlurpState.windowTitle = "Blurp: " + scriptName;
        Gdx.graphics.setTitle(BlurpState.windowTitle);
        BlurpConfiguration config = new BlurpConfiguration(viewportWidth, viewportHeight);
        BlurpRuntime blurpRuntime = BlurpRuntime.begin(config, mouseWindowChecker);

        blurpRuntime.onException(new BlurpExceptionHandler() {
            @Override
            public void handleException(RuntimeException e) {
                e.printStackTrace();
            }
        });
        String parent = new File(scriptName).getAbsoluteFile().getParent();
        if(parent != null) {
            config.setContentRoot(parent);
        }
        try {
            blurpRuntime.start(language, scriptName);
        } catch(RuntimeException e) {
            System.out.println("Error starting script " + scriptName);
            e.printStackTrace();
            Gdx.app.exit();
        }
        setScreen(BlurpStore.blurpScreen);
    }

    @Override
    public void dispose() {

        BlurpStore.dispose();
        Gdx.app.exit();
    }
}
