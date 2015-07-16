package com.bigcustard.blurp.bootstrap.languages;

public class JavaClassPath extends SupportedLanguage {

    public JavaClassPath() {

        super("Java class loaded from classpath");
    }

    @Override
    public Runner getRunner() {

        return new JavaClassPathRunner();
    }
}
