package com.bigcustard.blurp.bootstrap;

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

        BlurpRunnable script;
        try {
            Class<BlurpRunnable> scriptClass = (Class<BlurpRunnable>) Class.forName(scriptClassName);
            script = scriptClass.newInstance();
        } catch(Exception e) {
            throw new BlurpException("Error instantiating " + scriptClassName + " as an instance of BlurpRunnable");
        }

        blurpRuntime.start(script);
        setScreen(blurpRuntime.getScreen());
    }
}
