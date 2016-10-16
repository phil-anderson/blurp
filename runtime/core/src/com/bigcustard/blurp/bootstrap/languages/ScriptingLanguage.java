package com.bigcustard.blurp.bootstrap.languages;

import org.codehaus.groovy.jsr223.GroovyScriptEngineFactory;
import org.jruby.embed.jsr223.JRubyEngineFactory;
import org.python.jsr223.PyScriptEngineFactory;

import javax.script.ScriptEngine;

public class ScriptingLanguage extends SupportedLanguage {

    private static ScriptEngine groovy;
    private static ScriptEngine jruby;
    private static ScriptEngine jython;

    static {
        groovy = new GroovyScriptEngineFactory().getScriptEngine();
        jruby = new JRubyEngineFactory().getScriptEngine();
        jython = new PyScriptEngineFactory().getScriptEngine();
    }

    private final String name;

    protected ScriptingLanguage(String name, String description, String... fileExtensions) {
        super(description, fileExtensions);
        this.name = name;
    }

    public ScriptEngine getScriptingEngine() {
        String s = name.toUpperCase();
        if (s.equals("RUBY")) {
            return jruby;
        } else if (s.equals("GROOVY")) {
            return groovy;
        } else if (s.startsWith("PYTHON")) {
            return jython;
        } else {
            throw new IllegalArgumentException("Unknown scripting language: " + name);
        }
    }

    public Runner getRunner() {
        return new ScriptingLanguageRunner(this);
    }
}
