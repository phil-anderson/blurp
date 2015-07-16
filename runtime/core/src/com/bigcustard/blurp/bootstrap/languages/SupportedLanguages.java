package com.bigcustard.blurp.bootstrap.languages;

import java.lang.reflect.*;
import java.util.*;

public class SupportedLanguages {

    public static final SupportedLanguage JAVA_CLASS_PATH = new JavaClassPath();
    public static final SupportedLanguage JAVA_SOURCE_FILE = new JavaSourceFile();
    public static final SupportedLanguage JAVA_CLASS_FILE = new JavaClassFile();
    public static final SupportedLanguage GROOVY = new ScriptingLanguage("groovy", "Groovy scripts", "groovy", "gry", "gr", "gsh");
    public static final SupportedLanguage JAVA_SCRIPT = new ScriptingLanguage("javascript", "JavaScript scripts", "js");
    public static final SupportedLanguage PYTHON = new ScriptingLanguage("python [BETA]", "Python scripts", "py");
    public static final SupportedLanguage RUBY = new JRuby();

    private static List<SupportedLanguage> FILE_BASED_LANGUAGES = new ArrayList<SupportedLanguage>();
    static {
        for(Field field : SupportedLanguages.class.getFields()) {
            try {
                FILE_BASED_LANGUAGES.add((SupportedLanguage) field.get(null));
            } catch(IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static SupportedLanguage forFile(String fileName) {

        for(SupportedLanguage language : FILE_BASED_LANGUAGES) {
            if(language.acceptsFile(fileName)) return language;
        }
        // If no language found, assume it's a class name.
        return JAVA_CLASS_PATH;
    }

    public static Collection<SupportedLanguage> getAll() {

        return FILE_BASED_LANGUAGES;
    }
}