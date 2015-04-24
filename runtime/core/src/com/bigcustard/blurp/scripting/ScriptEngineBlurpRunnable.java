package com.bigcustard.blurp.scripting;

import java.io.*;
import javax.script.*;
import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class ScriptEngineBlurpRunnable implements BlurpRunnable {

    private final Reader scriptReader;
    private final ScriptEngine scriptEngine;
    private final String scriptName;

    public ScriptEngineBlurpRunnable(String language, Reader scriptReader, String scriptName) {

        this.scriptReader = scriptReader;
        this.scriptName = scriptName;
        scriptEngine = new ScriptEngineManager().getEngineByName(language);
        if(scriptEngine == null) throw new BlurpException("Couldn't get ScriptEngine for language name '" + language + "'");
    }

    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils, Keys keys, Colours colours) {

        scriptEngine.put("blurp", blurp);
        scriptEngine.put("screen", screen);
        scriptEngine.put("keyboard", keyboard);
        scriptEngine.put("utils", utils);
        scriptEngine.put("keys", keys);
        scriptEngine.put("colours", colours);
        scriptEngine.put(ScriptEngine.FILENAME, scriptName);
        try {
            scriptEngine.eval(scriptReader);
        } catch(ScriptException e) {
            throw new BlurpException("Error running script", e);
        }
    }
}
