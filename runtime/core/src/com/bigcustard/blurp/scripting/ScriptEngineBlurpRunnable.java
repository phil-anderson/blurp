package com.bigcustard.blurp.scripting;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import javax.script.*;
import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;

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
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils) {

        scriptEngine.put("blurp", blurp);
        scriptEngine.put("screen", screen);
        scriptEngine.put("keyboard", keyboard);
        scriptEngine.put("utils", utils);
        scriptEnginePutConstants(Colours.class);
        scriptEnginePutEnums(Justification.values());
        scriptEnginePutEnums(Handle.values());
        scriptEnginePutEnums(Key.values());
        scriptEnginePutEnums(CollisionShape.values());

        scriptEngine.put(ScriptEngine.FILENAME, scriptName);
        try {
            scriptEngine.eval(scriptReader);
        } catch(ScriptException e) {
            throw new BlurpException("Error running script", e);
        }
    }

    private void scriptEnginePutEnums(Enum[] enumValues){

        for(Enum enumValue : enumValues) {
            if(scriptEngine.get(enumValue.name()) != null) {
                throw new RuntimeException("Conflicting enumerations - " + enumValue.getClass() + " vs " + scriptEngine.get(enumValue.name()).getClass());
            }
            scriptEngine.put(enumValue.name(), enumValue);
        }
    }

    private void scriptEnginePutConstants(Class constantsClass) {

        List allConstants = new ArrayList();
        for(Field field : constantsClass.getFields()) {
            String fieldName = camelise(field.getName());
            if(scriptEngine.get(fieldName) != null) {
                throw new RuntimeException("Conflicting constants - " + constantsClass + " vs " + scriptEngine.get(fieldName).getClass());
            }
            try {
                Object constant = field.get(null);
                scriptEngine.put(fieldName, constant);
                allConstants.add(constant);
            } catch(IllegalAccessException e) {
                throw new IllegalStateException("Class " + constantsClass + " contains inaccessible, or non-constant fields");
            }
        }
        scriptEngine.put("All" + constantsClass.getSimpleName(), allConstants.toArray());
    }

    private String camelise(String capsName) {

        StringBuilder result = new StringBuilder();
        boolean wordBreak = true;

        for(int i = 0; i < capsName.length(); i++) {
            char ch = capsName.charAt(i);
            if(ch != '_') {
                result.append(wordBreak ? ch : Character.toLowerCase(ch));
                wordBreak = false;
            } else {
                wordBreak = true;
            }
        }
        return result.toString();
    }
}
