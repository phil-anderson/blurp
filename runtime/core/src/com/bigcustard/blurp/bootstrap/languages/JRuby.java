package com.bigcustard.blurp.bootstrap.languages;

import static com.bigcustard.blurp.bootstrap.languages.NamingStrategy.RUBY_CONSTANT;
import static com.bigcustard.blurp.bootstrap.languages.NamingStrategy.RUBY_GLOBAL;

public class JRuby extends ScriptingLanguage {

    protected JRuby() {

        super("ruby", "Ruby scripts [BETA]", "rb");
        globalStrategy = RUBY_GLOBAL;
        constantStrategy = RUBY_CONSTANT;
    }
}
