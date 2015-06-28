package com.bigcustard.blurp.bootstrap.languages;

import java.lang.reflect.*;
import java.util.*;

import static com.bigcustard.blurp.bootstrap.languages.NamingStrategy.*;

public class SupportedLanguages {

    public static final SupportedLanguage Java = new SupportedLanguage("java", "Java classes", PASS_THROUGH, "class");
    public static final SupportedLanguage JavaScript = new SupportedLanguage("javascript", "JavaScript scripts", PASS_THROUGH, "js");
    public static final SupportedLanguage Python = new SupportedLanguage("python", "Python scripts", PASS_THROUGH, "py");
    public static final SupportedLanguage Groovy = new SupportedLanguage("groovy", "Groovy scripts", PASS_THROUGH, "groovy", "gry", "gr", "gsh");
    public static final SupportedLanguage Ruby = new SupportedLanguage("ruby", "Ruby scripts [WIP]", RUBY_CONSTANT, "rb");

    private static Map<String, SupportedLanguage> ALL_BY_NAME = new HashMap<String, SupportedLanguage>();
    static {
        for(Field field : SupportedLanguages.class.getFields()) {
            try {
                ALL_BY_NAME.put(field.getName(), (SupportedLanguage)field.get(null));
            } catch(IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static SupportedLanguage forName(String name) {

        return ALL_BY_NAME.get(name);
    }

    public static SupportedLanguage forFile(String fileName) {

        for(SupportedLanguage language : ALL_BY_NAME.values()) {
            if(language.acceptsFile(fileName)) return language;
        }
        return null;
    }

    public static Collection<SupportedLanguage> getAll() {

        return ALL_BY_NAME.values();
    }
}