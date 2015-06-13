package com.bigcustard.blurp.model;

public class Viewport {

    public double width, height;
    public boolean stretchToFit;

    public Viewport width(double newViewportWidth) {

        width = newViewportWidth;
        return this;
    }

    public Viewport height(double newViewportHeight) {

        height = newViewportHeight;
        return this;
    }

    public Viewport stretchToFit(boolean newViewportStretch) {

        this.stretchToFit = newViewportStretch;
        return this;
    }

    public Viewport size(double newViewportWidth, double newViewportHeight) {

        width = newViewportWidth;
        height = newViewportHeight;
        return this;
    }
}
