package com.bigcustard.blurp.bootstrap.languages;

import com.bigcustard.blurp.core.*;

public class JavaClassPathRunner extends JavaClassRunner {

    @Override
    public void prepare(String className) {

        try {
            classToRun = Class.forName(className);
        } catch(ClassNotFoundException e) {
            throw new BlurpException("Couldn't determine language based on the name. Tried to load as a Java class, but couldn't find find it on classpath: " + className);
        }
    }
}
