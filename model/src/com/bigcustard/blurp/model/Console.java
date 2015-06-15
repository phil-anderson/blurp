package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.constants.*;

public abstract class Console {

    public Colour colour = Colours.LIGHT_GREY;
    public double transparency = 1;

    public Console colour(Colour newColour) {

        this.colour = newColour;
        return this;
    }

    public Console transparency(double newAlpha) {

        this.transparency = newAlpha;
        return this;
    }

    public Console printLine(Object objectToPrint) {

        return println(objectToPrint);
    }

    public Console println(Object objectToPrint) {

        return print(objectToPrint + "\n");
    }

    public abstract Console clear();

    public abstract Console print(Object objectToPrint);
}
