package com.bigcustard.blurp.core;

import com.badlogic.gdx.graphics.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.util.*;

public class BlurpState {

    public static boolean debugMode;
    public static Colour debugColour = Colours.LimeGreen;
    public static boolean scriptComplete;
    public static RuntimeException exception;
    public static long frameStartTime;
    public static boolean error;

    public static Color debugGdxColour() {

        return Convert.toGdxColour(debugColour);
    }

    public static void reset() {

        debugMode = false;
        debugColour = Colours.LimeGreen;
        scriptComplete = false;
        exception = null;
        error = false;
    }
}
