package com.bigcustard.blurp.scripting;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import javax.script.*;
import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;

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
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils, Effects effects) {

        Bindings bindings = scriptEngine.createBindings();
        bindings.put("blurp", blurp);
        bindings.put("screen", screen);
        bindings.put("keyboard", keyboard);
        bindings.put("utils", utils);
        scriptEnginePutConstants(Colours.class, bindings);
        scriptEnginePutEnums(Justification.values(), bindings);
        scriptEnginePutEnums(Handle.values(), bindings);
        scriptEnginePutEnums(Key.values(), bindings);
        scriptEnginePutEnums(CollisionShape.values(), bindings);

        bindings.put(ScriptEngine.FILENAME, scriptName);
        try {
            scriptEngine.eval(scriptReader, bindings);
        } catch(ScriptException e) {
            throw new BlurpException("Error running script", e);
        }
    }

    private void scriptEnginePutEnums(Enum[] enumValues, Bindings bindings){

        for(Enum enumValue : enumValues) {
            if(bindings.get(enumValue.name()) != null) {
                throw new RuntimeException("Conflicting enumerations - " + enumValue.getClass() + " vs " + bindings.get(enumValue.name()).getClass());
            }
            bindings.put(enumValue.name(), enumValue);
        }
    }

    private void scriptEnginePutConstants(Class constantsClass, Bindings bindings) {

        List allConstants = new ArrayList();
        for(Field field : constantsClass.getFields()) {
            String fieldName = camelise(field.getName());
            if(bindings.get(fieldName) != null) {
                throw new RuntimeException("Conflicting constants - " + constantsClass + " vs " + bindings.get(fieldName).getClass());
            }
            try {
                Object constant = field.get(null);
                bindings.put(fieldName, constant);
                allConstants.add(constant);
            } catch(IllegalAccessException e) {
                throw new IllegalStateException("Class " + constantsClass + " contains inaccessible, or non-constant fields");
            }
        }
        bindings.put("All" + constantsClass.getSimpleName(), allConstants.toArray());
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
