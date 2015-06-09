package com.bigcustard.blurp.model;

public class NamedColour extends Colour {

    public final String name;

    public NamedColour(double red, double green, double blue, String name) {

        super(red, green, blue);
        this.name = name;
    }

    @Override
    public String toString() {

        return name;
    }
}
