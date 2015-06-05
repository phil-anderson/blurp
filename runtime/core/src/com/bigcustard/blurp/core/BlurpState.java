package com.bigcustard.blurp.core;

import com.badlogic.gdx.graphics.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.util.*;

public class BlurpState {

    public static boolean debugMode;

    public static Colour debugColour;

    public static boolean scriptComplete;

    public static Color debugGdxColour() {

        return Convert.toGdxColour(debugColour);
    }

    public static void reset() {

        debugMode = false;
        debugColour = Colours.LIME_GREEN;
        scriptComplete = false;
    }
}
