package com.bigcustard.blurp.scripting;

import com.badlogic.gdx.Gdx;
import com.bigcustard.blurp.core.BlurpException;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class JRubyWrapperSpike {
    public static void wrap(ScriptEngine scriptEngine, Bindings bindings) {
        InputStream stream = Gdx.files.classpath("wrapper.rb").read();
        try {
            bindings.put("bindings", bindings);
            Map<String, Object> wrappedBindings = (Map<String, Object>) scriptEngine.eval(new InputStreamReader(stream), bindings);
            for (String key : wrappedBindings.keySet()) {
                bindings.put(key, wrappedBindings.get(key));
            }
        } catch (ScriptException e) {
            throw new BlurpException("Error running jruby wrapper script", e);
        }
    }
}
