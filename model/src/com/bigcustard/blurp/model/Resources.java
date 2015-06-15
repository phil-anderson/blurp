package com.bigcustard.blurp.model;

public abstract class Resources {

    public abstract Image loadImage(String filename);

    /**
     * Constructs a new ImageSprite using the image file specified, and places it at the center of the Screen.
     * <p>
     * A new Image will be created just for this ImageSprite, and the ImageSprite's image property will be set
     * accordingly.
     * <p>
     * If you want more than one ImageSprite to share the same image, then you should create a single {@link Image}
     * object for the image and construct all of the ImageSprites using that instead. It'll be more efficient as the
     * image will only be loaded once.
     *
     * @param imageFilename The name of the image file to load
     */
    public abstract ImageSprite createImageSprite(String imageFilename);

    public abstract ImageSprite createImageSprite(Image image);

    public abstract TextSprite createTextSprite(String text);

    public abstract Colour createColour(double red, double green, double blue);
}
