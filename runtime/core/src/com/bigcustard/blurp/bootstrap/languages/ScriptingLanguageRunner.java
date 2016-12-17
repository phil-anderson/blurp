package com.bigcustard.blurp.bootstrap.languages;

import com.bigcustard.blurp.core.BlurpException;
import com.bigcustard.blurp.core.BlurpStore;
import com.bigcustard.blurp.model.constants.Colours;
import com.bigcustard.blurp.model.constants.EffectStyle;
import com.bigcustard.blurp.model.constants.ExistingEffectStrategy;
import com.bigcustard.blurp.model.constants.Handle;
import com.bigcustard.blurp.model.constants.Justification;
import com.bigcustard.blurp.model.constants.ScreenLayer;
import com.bigcustard.blurp.model.constants.SpriteEventHandlers;
import com.bigcustard.blurp.model.constants.TargetStyle;
import com.bigcustard.blurp.util.Files;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ScriptingLanguageRunner implements Runner {

    private final ScriptingLanguage language;

    private String scriptFilename;

    public ScriptingLanguageRunner(ScriptingLanguage language) {

        this.language = language;
    }

    public void prepare(String scriptFilename) {

        this.scriptFilename = scriptFilename;
    }

    @Override
    public void run() {

        ScriptEngine scriptingEngine = language.getScriptingEngine();
        Bindings bindings = scriptingEngine.getBindings(ScriptContext.ENGINE_SCOPE);
        bindings.clear();
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
            Reader scriptReader = Files.getFile(scriptFilename).reader();
            scriptingEngine.eval(scriptReader, bindings);
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
}
