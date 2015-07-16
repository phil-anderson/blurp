package com.bigcustard.blurp.bootstrap.languages;

public class JavaClassFile extends SupportedLanguage {

    public JavaClassFile() {

        super("Java class files", "class");
    }

    @Override
    public Runner getRunner() {

        return new JavaClassFileRunner();
    }
}
