package com.bigcustard.blurp.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.viewport.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class Blurp extends Game {

    private String scriptClassName;
    private Viewport viewport;

    public Blurp(String scriptClassName, Viewport viewport) {

        this.scriptClassName = scriptClassName;
        this.viewport = viewport;
    }

    // TODO - Need to override other lifecycle methods
    @Override
    public void create() {

        BlurpRuntime blurpRuntime = BlurpRuntime.begin(viewport);

        BlurpMain script;
        try {
            Class<BlurpMain> scriptClass = null;
            scriptClass = (Class<BlurpMain>) Class.forName(scriptClassName);
            script = scriptClass.newInstance();
        } catch(Exception e) {
            throw new BlurpException("Error instantiating " + scriptClassName + " as an instance of BlurpMain");
        }

        blurpRuntime.start(script);
        setScreen(blurpRuntime.getScreen());
    }
}
