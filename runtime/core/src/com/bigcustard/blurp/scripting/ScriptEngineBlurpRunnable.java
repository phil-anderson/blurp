package com.bigcustard.blurp.scripting;

import java.io.*;
import javax.script.*;
import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class ScriptEngineBlurpRunnable implements BlurpRunnable {

    private Reader scriptReader;
    private ScriptException scriptException;
    private ScriptEngine scriptEngine;

    public ScriptEngineBlurpRunnable(String language, Reader scriptReader) {

        this.scriptReader = scriptReader;
        scriptEngine = new ScriptEngineManager().getEngineByName(language);
        if(scriptEngine == null) throw new BlurpException("Couldn't get ScriptEngine for language name '" + language + "'");
    }

    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils, Keys keys) {

        scriptEngine.put("blurp", blurp);
        scriptEngine.put("screen", screen);
        scriptEngine.put("keyboard", keyboard);
        scriptEngine.put("utils", utils);
        scriptEngine.put("keys", keys);
        try {
            scriptEngine.eval(scriptReader);
        } catch(ScriptException e) {
            scriptException = e;
        }
    }

    public boolean isErrors() {

        return scriptException != null;
    }

    public ScriptException getScriptException() {

        return scriptException;
    }
}
