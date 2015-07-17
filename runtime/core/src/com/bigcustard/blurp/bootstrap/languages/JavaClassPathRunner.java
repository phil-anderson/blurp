package com.bigcustard.blurp.bootstrap.languages;

import com.bigcustard.blurp.core.*;

public class JavaClassPathRunner extends JavaClassRunner {

    @Override
    public void prepare(String className) {

        try {
            classToRun = Class.forName(className);
        } catch(ClassNotFoundException e) {
            throw new BlurpException("Couldn't determine language of " + className + ", and couldn't find a class with that name on the classpath.");
        }
    }
}
