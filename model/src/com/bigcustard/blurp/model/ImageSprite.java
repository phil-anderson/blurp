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

    private boolean boundImage;

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
        boundImage = true;
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

    /**
     * First off, let me say that you'll almost certainly never need to use this method. Seriously. Move on. You have
     * better things to do with your time.
     * <p>
     * Still reading? Excellent! Clearly you're curious, and curiosity is a wondeful thing as far as I'm concerned.
     * So... What's this method all about then?
     * <p>
     * If you created this ImageSprite by specifying an image filename then behind the scenes Blurp will have created
     * its own Image and used that. That Image is said to be "bound" to the ImageSprite because the ImageSprite is the
     * only thing that knows about it and is the only thing that uses it.
     * <p>
     * When you remove the ImageSprite, Blurp will check whether the image was bound using this method, and if it was
     * it will also remove the image.
     *
     * @return True if the ImageSprite was created with an image filename and so had to create an Image itself.
     */
    public boolean isImageBound() {

        return boundImage;
    }
}
