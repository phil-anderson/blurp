package com.bigcustard.blurp.model;

public class Viewport {

    public double width, height;
    public boolean stretchToFit;

    public Viewport setWidth(double newViewportWidth) {

        width = newViewportWidth;
        return this;
    }

    public Viewport setHeight(double newViewportHeight) {

        height = newViewportHeight;
        return this;
    }

    public Viewport setStretchToFit(boolean newViewportStretch) {

        this.stretchToFit = newViewportStretch;
        return this;
    }

    public Viewport setSize(double newViewportWidth, double newViewportHeight) {

        width = newViewportWidth;
        height = newViewportHeight;
        return this;
    }

    @Override
    public String toString() {

        return String.format("Viewport: width=%.1f height=%.1f stretchToFit=%s", width, height, stretchToFit);
    }
}
