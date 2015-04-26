package com.bigcustard.blurp.model;

/**
 * ImageSprites are the most commonly used type of {@link Sprite}. They (sort of) turn {@link Image Images} into
 * objects that can move around the screen and that the user can interact with.
 *
 * ImageSprites <i>extend</i> Sprite, which means that anything you can do with a Sprite, you can also do with an
 * ImageSprite, plus some extra stuff that is specific to ImageSprites.
 *
 * @see Sprite
 * @see Image
 */
public abstract class ImageSprite extends Sprite<ImageSprite> {

    /**
     * The {@link Image} that this ImageSprite will display
     */
    public Image image;

    public ImageSprite image(Image newImage) {

        image = newImage;
        return this;
    }

    /**
     * Removes the ImageSprite completely from Blurp. It will be destroyed, and no longer appear on screen. Other
     * ImageSprites that use the same Image will (of course) be fine.
     * <p>
     * Removing things from Blurp when you no longer need them is a really good thing to do, as any resources they use
     * up is freed and available for the rest of your program to use.
     */
    public abstract void remove();
}
