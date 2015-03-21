package com.bigcustard.blurp.model;

/**
 * An Image object is a holder for pictures and images that you want to use in your program. They load the image file
 * and make it available for use by Blurp.
 *
 * Images can be used to construct {@link ImageSprite ImageSprites} which then allow you to whiz them around the screen,
 * grow or shrink them, rotate them and more.
 *
 * You can use the same Image in many ways and for many different ImageSprites, and it will only need to be loaded once.
 * This makes your program more efficient, and therefore run more smoothly.
 */
public class Image {

    /**
     * The name of the image file that this image was created with. This property is read-only.
     */
    public final String filename;

    /**
     * Construct an Image object from the image file provided.
     * @param filename The filename of the image to load.
     */
    public Image(String filename) {

        if(filename == null) throw new RuntimeException("Image filename must not be null");
        this.filename = filename;
        Repository.getInstance().addImage(this);
    }

    @Override
    public boolean equals(Object other) {

        return this == other || (other instanceof Image && filename.equals(((Image) other).filename));
    }

    @Override
    public int hashCode() {

        return filename.hashCode();
    }
}
