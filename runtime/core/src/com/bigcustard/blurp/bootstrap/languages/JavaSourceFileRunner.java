package com.bigcustard.blurp.bootstrap.languages;

import java.io.*;
import javax.tools.*;
import com.bigcustard.blurp.core.*;

public class JavaSourceFileRunner implements Runner {

    private JavaClassFileRunner delegateRunner;

    @Override
    public void prepare(String filename) {

        compile(filename);
        delegateRunner = new JavaClassFileRunner();

        // TODO: Better way of being case insensitive.
        String classFilename = filename.replace(".java", ".class")
                                       .replace(".Java", ".class")
                                       .replace(".JAVA", ".class");
        delegateRunner.prepare(classFilename);
    }

    @Override
    public void run() {

        delegateRunner.run();
    }

    private void compile(String filename) {

        StringWriter results = new StringWriter();

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        // TODO: ANDROID - Need to implement a custom JavaFileHandler for cross-platform support
        // TODO: Should probably also reuse a single file manager as an optimisation
        Iterable<? extends JavaFileObject> javaFileObjects = fileManager.getJavaFileObjects(filename);
        if(!compiler.getTask(results, fileManager, null, null, null, javaFileObjects).call()) {
            throw new BlurpException(results.toString());
        }
        System.out.println(results.toString());

        try {
            fileManager.close();
        } catch(IOException e) {
            throw new BlurpException("Error closing Java file manager", e);
        }
    }
}
