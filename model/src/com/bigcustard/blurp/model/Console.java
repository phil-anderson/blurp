package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.constants.*;

public abstract class Console {

    public Colour colour = Colours.LIGHT_GREY;

    public Console colour(Colour newColour) {

        this.colour = newColour;
        return this;
    }

    public Console println(String textToPrint) {

        return print(textToPrint + "\n");
    }

    public abstract Console clear();

    public abstract Console print(String textToPrint);
}
