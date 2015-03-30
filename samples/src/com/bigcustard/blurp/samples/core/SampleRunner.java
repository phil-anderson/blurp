package com.bigcustard.blurp.samples.core;


import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.desktop.*;

public abstract class SampleRunner extends Blurp {

    public static void main(String[] args) {

        if(args.length != 1) throw new IllegalArgumentException("Usage: SampleRunner <sample class name>");

        String className = args[0];
        if(SampleRunner.class.getClassLoader().getResource(className) == null) {
            // Perhaps they haven't fully qualified the class name
            className = "com.bigcustard.blurp.samples." + className;
        }

        args = new String[] { className, "800", "600" };
        DesktopLauncher.main(args);
    }
}
