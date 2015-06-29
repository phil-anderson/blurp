package com.bigcustard.blurp.scripting;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import javax.script.*;
import com.bigcustard.blurp.bootstrap.languages.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.util.*;

public class ScriptEngineBlurpRunnable implements Runnable {

    private final SupportedLanguage language;
    private final String scriptFilename;
    private final String scriptContents;
    private final ScriptEngine scriptEngine;

    public ScriptEngineBlurpRunnable(SupportedLanguage language, String scriptFilename) {

        this.language = language;
        this.scriptFilename = scriptFilename;
        this.scriptContents = null;
        scriptEngine = new ScriptEngineManager().getEngineByName(language.getName());
        if(scriptEngine == null) throw new BlurpException("Couldn't get ScriptEngine for language name '" + language.getName() + "'");
    }

    public ScriptEngineBlurpRunnable(SupportedLanguage language, String scriptFilename, String scriptContents) {

        this.language = language;
        this.scriptFilename = scriptFilename;
        this.scriptContents = scriptContents;
        scriptEngine = new ScriptEngineManager().getEngineByName(language.getName());
        if(scriptEngine == null) throw new BlurpException("Couldn't get ScriptEngine for language name '" + language.getName() + "'");
    }

    @Override
    public void run() {

        Bindings bindings = scriptEngine.createBindings();

        bindings.put("system", BlurpStore.system);
        bindings.put("screen", BlurpStore.modelScreen);
        bindings.put("camera", BlurpStore.modelCamera);
        bindings.put("console", BlurpStore.console);
        bindings.put("resources", BlurpStore.resources);
        bindings.put("effects", BlurpStore.effects);
        bindings.put("keyboard", BlurpStore.keyboard);
        bindings.put("mouse", BlurpStore.modelMouse);
        bindings.put("timer", BlurpStore.timer);
        bindings.put("utils", BlurpStore.utils);

        scriptEnginePutConstants(SpriteEventHandlers.class, bindings);
        scriptEnginePutConstants(Colours.class, bindings);

        scriptEnginePutEnums(Justification.values(), bindings);
        scriptEnginePutEnums(Handle.values(), bindings);
        scriptEnginePutEnums(TargetStyle.values(), bindings);
        scriptEnginePutEnums(EffectStyle.values(), bindings);
        scriptEnginePutEnums(ExistingEffectStrategy.values(), bindings);
        scriptEnginePutEnums(ScreenLayer.values(), bindings);

        bindings.put(ScriptEngine.FILENAME, scriptFilename);

        try {
            runScript(bindings);
        } catch(RuntimeException e) {
            throw e; // Rethrow
        } catch(Exception e) {
            throw new BlurpException("Error running script", e);
        }
    }

    private void scriptEnginePutEnums(Enum[] enumValues, Bindings bindings){

        for(Enum enumValue : enumValues) {
            if(bindings.get(enumValue.name()) != null) {
                throw new RuntimeException("Conflicting enumerations - " + enumValue.getClass() + " vs " + bindings.get(enumValue.name()).getClass());
            }
            String enumName = language.getConstantStrategy().transform(enumValue.name());
            bindings.put(enumName, enumValue);
        }
    }

    private void scriptEnginePutConstants(Class constantsClass, Bindings bindings) {

        List allConstants = new ArrayList();
        for(Field field : constantsClass.getFields()) {
            String fieldName = field.getName();
            fieldName = language.getConstantStrategy().transform(fieldName);
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

    private void runScript(Bindings bindings) throws FileNotFoundException, ScriptException {

        if(scriptContents != null) {
            scriptEngine.eval(scriptContents, bindings);
        } else {
            Reader scriptReader = Files.getFile(scriptFilename).reader();
            scriptEngine.eval(scriptReader, bindings);
        }
    }
}
