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

    public static String toCamelCase(String capsName) {

        StringBuilder result = new StringBuilder();
        boolean wordBreak = true;

        for(int i = 0; i < capsName.length(); i++) {
            char ch = capsName.charAt(i);
            if(ch != '_') {
                result.append(wordBreak ? ch : Character.toLowerCase(ch));
                wordBreak = false;
            } else {
                wordBreak = true;
            }
        }
        return result.toString();
    }

}
