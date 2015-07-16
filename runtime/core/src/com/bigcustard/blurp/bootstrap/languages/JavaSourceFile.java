package com.bigcustard.blurp.bootstrap.languages;

public class JavaSourceFile extends SupportedLanguage {

    protected JavaSourceFile() {

        super("Java source files", "java");
    }

    @Override
    public Runner getRunner() {

        return new JavaSourceFileRunner();
    }
}
