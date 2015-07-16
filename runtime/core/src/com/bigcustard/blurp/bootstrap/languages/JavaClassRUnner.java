package com.bigcustard.blurp.bootstrap.languages;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.java.*;
import com.bigcustard.blurp.model.java.bootstrap.*;

public abstract class JavaClassRunner implements Runner{

    protected Class classToRun;

    @Override
    public void run() {

        BlurpJavaProgram instance;
        try {
            JavaBootstrapHolder.initialise(new JavaBootstrapImpl());
            instance = (BlurpJavaProgram) classToRun.newInstance();
        } catch(Exception e) {
            throw new BlurpException("Error instantiating class " + classToRun.getName());
        }
        instance.run();
    }
}
