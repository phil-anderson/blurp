package com.bigcustard.blurp.util;

import com.badlogic.gdx.graphics.*;
import com.bigcustard.blurp.model.*;

public class Convert {

    public static Color toGdxColour(Colour colour) {

        return toGdxColour(colour, 1);
    }

    public static Color toGdxColour(Colour colour, double alpha) {

        return new Color((float) colour.red,
                         (float) colour.green,
                         (float) colour.blue,
                         (float) alpha);
    }
}
