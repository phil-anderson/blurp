package com.bigcustard.blurp.scripting;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import javax.script.*;
import com.bigcustard.blurp.bootstrap.languages.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.util.*;
import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.jruby.embed.jsr223.JRubyEngineFactory;

public class ScriptEngineRunner implements Runnable {

    private final SupportedLanguage language;
    private final String scriptFilename;
    private final String scriptContents;
    private final ScriptEngine scriptEngine;

    public ScriptEngineRunner(SupportedLanguage language, String scriptFilename) {
        this(language, scriptFilename, null);
    }

    public ScriptEngineRunner(SupportedLanguage language, String scriptFilename, String scriptContents) {
        this.language = language;
        this.scriptFilename = scriptFilename;
        this.scriptContents = scriptContents;
        scriptEngine = getScriptEngineManager().getEngineByName(language.getName());
        if(scriptEngine == null) throw new BlurpException("Couldn't get ScriptEngine for language name '" + language.getName() + "'");
    }

    private ScriptEngineManager getScriptEngineManager() {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        scriptEngineManager.registerEngineName("ruby", new JRubyEngineFactory());
        scriptEngineManager.registerEngineName("groovy", new GroovyScriptEngineFactory());
        return scriptEngineManager;
    }

    @Override
    public void run() {

        Bindings bindings = scriptEngine.createBindings();

        bindings.put(language.getGlobalStrategy().transform("system"), BlurpStore.system);
        bindings.put(language.getGlobalStrategy().transform("screen"), BlurpStore.modelScreen);
        bindings.put(language.getGlobalStrategy().transform("camera"), BlurpStore.modelCamera);
        bindings.put(language.getGlobalStrategy().transform("console"), BlurpStore.console);
        bindings.put(language.getGlobalStrategy().transform("resources"), BlurpStore.resources);
        bindings.put(language.getGlobalStrategy().transform("effects"), BlurpStore.effects);
        bindings.put(language.getGlobalStrategy().transform("keyboard"), BlurpStore.keyboard);
        bindings.put(language.getGlobalStrategy().transform("mouse"), BlurpStore.modelMouse);
        bindings.put(language.getGlobalStrategy().transform("timer"), BlurpStore.timer);
        bindings.put(language.getGlobalStrategy().transform("utils"), BlurpStore.utils);

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
