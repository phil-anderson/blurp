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
     * Constructs a new ImageSprite using the image file specified, and places it at the X and Y coordinates specified.
     * A new Image will be created just for this ImageSprite, and the {@link #image} property will be set accordingly.
     * <p>
     * If you want more than one ImageSprite to share the same image, then you should create a single {@link Image}
     * object for the image and construct all of the ImageSprites using that instead. It'll be more efficient as the
     * image will only be loaded once.
     *
     * @param imageFileName The name of the image file to load
     * @param x The X coordinate
     * @param y The Y coordinate
     */
    public ImageSprite(String imageFileName, double x, double y) {

        this(new Image(imageFileName), x, y);
    }


    /**
     * Constructs a new ImageSprite using the {@link Image} specified.
     *
     * @param image The {@link Image} object to use
     */
    public ImageSprite(Image image) {

        // TODO: Better defaults
        this(image, 0, 0);
    }

    /**
     * Constructs a new ImageSprite using the {@link Image} specified, and places it at the X and Y coordinates
     * specified.
     *
     * @param image The image to use
     * @param x The X coordinate
     * @param y The Y coordinate
     */
    public ImageSprite(Image image, double x, double y) {

        this(image, x, y, 1, 1, 0);
    }

    /**
     * Constructs a new ImageSprite using the {@link Image} specified, places it at the X and Y coordinates specified,
     * sets its x and y scales to those provided, and gives it teh specified rotation.
     *
     * @param image The image to use
     * @param x The X coordinate
     * @param y The Y coordinate
     * @param scaleX The scaling factor of the ImageSprite along the X axis
     * @param scaleY The scaling factor of the ImageSprite along the Y axis
     * @param rotation The angle (in degrees) that the ImageSprite should be rotated by
     */
    public ImageSprite(Image image, double x, double y, double scaleX, double scaleY, double rotation) {

        this.image = image;
        this.setPosition(x, y);
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.rotation = rotation;

        ModelRepository.getInstance().addImageSprite(this);
    }

    /**
     * Removes the ImageSprite completely from Blurp. It will be destroyed, and no longer appear on screen. Other
     * ImageSprites that use the same Image will (of course) be fine.
     * <p>
     * Removing things from Blurp when you no longer need them is a really good thing to do, as any resources they use
     * up is freed and available for the rest of your program to use.
     */
    public void remove() {

        ModelRepository.getInstance().removeImageSprite(this);
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

    public void dispose() {

        remove();
    }
}
