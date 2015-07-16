package com.bigcustard.blurp.bootstrap.languages;

import javax.script.*;
import com.bigcustard.blurp.core.*;
import org.jruby.embed.jsr223.*;

public class ScriptingLanguage extends SupportedLanguage {

    private final String name;

    protected ScriptingLanguage(String name, String description, String... fileExtensions) {

        super(description, fileExtensions);
        this.name = name;
    }

    public ScriptEngine getScriptingEngine() {

        ScriptEngine scriptEngine = getScriptEngineManager().getEngineByName(name);
        if(scriptEngine == null) {
            throw new BlurpException("Couldn't get ScriptEngine for language name '" + name + "'");
        }

        return scriptEngine;
    }

    public Runner getRunner() {

        return new ScriptingLanguageRunner(this);
    }

    private ScriptEngineManager getScriptEngineManager() {

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        scriptEngineManager.registerEngineName("ruby", new JRubyEngineFactory());
//        scriptEngineManager.registerEngineName("groovy", new GroovyScriptEngineFactory());
        return scriptEngineManager;
    }
}
