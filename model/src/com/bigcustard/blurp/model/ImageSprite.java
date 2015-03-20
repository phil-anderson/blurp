package com.bigcustard.blurp.model;

/**
 * ImageSprites are the most commonly used type of {@link Sprite}. They (sort of) turn {@link Image Images} into
 * objects that can move around the screen and that the user can interact with.
 *
 * ImageSprites <i>extend</i> Sprite, which means that anything you can do with a Sprite, you can also do with an
 * ImageSprite, plus some extra stuff that is specific to ImageSprites.
 */
public class ImageSprite extends Sprite<ImageSprite> {


    public Image image;

    /**
     * Constructs a new ImageSprite using the image file specified. The {@link #image} property will be set accordingly.
     * <p>
     * If you want more than one ImageSprite to share the same image, then you should create a single {@link Image}
     * object for the image and construct the imagesprites using that instead as it'll be more efficient.
     *
     * @param imageFileName The name of the image file to load
     */
    public ImageSprite(String imageFileName) {

        this(new Image(imageFileName));
    }

    /**
     * Constructs a new ImageSprite using the {@link Image} specified. This is the preferred way of creating
     * ImageSprites.
     *
     * @param image The {@link Image} object to use
     */
    public ImageSprite(Image image) {

        this.image = image;
        Repository.getInstance().addImageSprite(this);
    }

    /**
     * Flips the ImageSprite on the X-axis (i.e. from left-to-right), so it looks like a mirror-image of the original.
     *
     * @return Itself
     */
    public ImageSprite flipX() {

        scaleX = -scaleX;
        return this;
    }

    /**
     * Flips the ImageSprite on the Y-axis (i.e. from top-to-bottom), so it looks upside-down.
     *
     * @return Itself
     */
    public ImageSprite flipY() {

        scaleY = -scaleY;
        return this;
    }
}
