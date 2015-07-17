package com.bigcustard.blurp.desktop;

import java.io.*;
import javax.swing.*;
import com.badlogic.gdx.*;
import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.bootstrap.languages.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.ui.*;
import com.bigcustard.blurp.util.*;

public class BlurpApp extends Game {

    private static final String[] CLOSE_BUTTON = new String[] { "Close" };

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
            if(Gdx.app.getType() == Application.ApplicationType.Desktop) {
                showErrorDialog(e);
            }

            System.out.println("Error starting script " + scriptName);
            e.printStackTrace();
            Gdx.app.exit();
        }
        setScreen(BlurpStore.blurpScreen);
    }

    public void showErrorDialog(RuntimeException e) {

        // In case jar wasn't run from a console
        JOptionPane.showOptionDialog(null,
                                     Convert.toWrapped(e.getMessage(), 40) + "\n\nClosing Blurp.",
                                     "Blurp Error",
                                     JOptionPane.DEFAULT_OPTION,
                                     JOptionPane.ERROR_MESSAGE,
                                     null,
                                     CLOSE_BUTTON,
                                     null);
    }

    @Override
    public void dispose() {

        BlurpStore.dispose();
        Gdx.app.exit();
    }
}
