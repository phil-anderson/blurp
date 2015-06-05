package com.bigcustard.blurp.core;

import com.badlogic.gdx.graphics.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.util.*;

public class BlurpState {

    public static boolean debugMode;

    public static Color debugColour;

    public static boolean scriptComplete;

    public static void reset() {

        debugMode = false;
        debugColour = Convert.toGdxColour(Colours.LIME_GREEN);
        scriptComplete = false;
    }
}
