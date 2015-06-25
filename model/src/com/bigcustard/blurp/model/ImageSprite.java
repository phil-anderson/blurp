package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.constants.*;

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

    public ImageSprite setImage(Image newImage) {

        image = newImage;
        targetStyle = TargetStyle.Circle;
        return this;
    }

    @Override
    public String toString() {

        return String.format("ImageSprite: image=%s ", image.name) + super.toString();
    }
}
