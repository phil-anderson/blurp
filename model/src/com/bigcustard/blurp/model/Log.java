package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.constants.*;

public abstract class Log {

    public Colour colour = Colours.LIGHT_GREY;
    public double transparency = 1;

    public Log colour(Colour newColour) {

        this.colour = newColour;
        return this;
    }

    public Log transparency(double newAlpha) {

        this.transparency = newAlpha;
        return this;
    }

    public Log println(Object objectToPrint) {

        return print(objectToPrint + "\n");
    }

    public abstract Log clear();

    public abstract Log print(Object objectToPrint);
}
