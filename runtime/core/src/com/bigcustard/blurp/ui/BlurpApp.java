package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.core.*;

public class BlurpApp extends Game {

    private String scriptClassName;
    private Viewport viewport;

    public BlurpApp(String scriptClassName, Viewport viewport) {

        this.scriptClassName = scriptClassName;
        this.viewport = viewport;
    }

    // TODO - Need to override other lifecycle methods
    @Override
    public void create() {

        BlurpRuntime blurpRuntime = BlurpRuntime.begin(viewport);

        IBlurpRunnable script;
        try {
            Class<IBlurpRunnable> scriptClass = (Class<IBlurpRunnable>) Class.forName(scriptClassName);
            script = scriptClass.newInstance();
        } catch(Exception e) {
            throw new BlurpException("Error instantiating " + scriptClassName + " as an instance of Blurp");
        }

        blurpRuntime.start(script);
        setScreen(blurpRuntime.getScreen());
    }
}
