package com.bigcustard.blurp.samples.core;


import com.bigcustard.blurp.desktop.*;
import com.bigcustard.blurp.model.*;

public abstract class RunnableSample extends BlurpMain {

    // TODO: Can't think of a less intrusive way to get the subclass's Class from a static context
    public static Class clarse;

    public static void main(String[] args) {

        args = new String[] { clarse.getCanonicalName(), "800", "600" };
        DesktopLauncher.main(args);
    }
}
