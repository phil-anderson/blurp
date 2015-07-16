package com.bigcustard.blurp.bootstrap.languages;

import java.io.*;
import java.net.*;
import com.badlogic.gdx.*;
import com.bigcustard.blurp.core.*;

public class JavaClassFileRunner extends JavaClassRunner {

    @Override
    public void prepare(String filename) {

        if(Gdx.app.getType() != Application.ApplicationType.Desktop) {
            throw new BlurpException("Java is currently only supported on desktop computers");
        }

        try {
            // TODO: Currently insists on no package statement.
            File classFile = new File(filename);
            URLClassLoader loader = new URLClassLoader(new URL[] { classFile.getParentFile().toURI().toURL()}, this.getClass().getClassLoader());

            String className = classFile.getName().replace(".class", "");
            classToRun = loader.loadClass(className);
        } catch(Exception e) {
            throw new BlurpException("Can't find class " + filename);
        }
    }
}
