package com.bigcustard.blurp.model;

public abstract class Resources {

    public abstract ImageSprite createImageSprite(String imageFilename);

    public abstract AnimationSprite createAnimationSprite(String... imageFilenames);

    public abstract TextSprite createTextSprite(String text);

    public abstract Colour createColour(double red, double green, double blue);
}
