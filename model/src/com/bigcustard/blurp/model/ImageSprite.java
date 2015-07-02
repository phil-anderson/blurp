package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.constants.*;

/**
 * ImageSprites are the most commonly used type of {@link Sprite}. They turn images into objects that can move around
 * the screen and that the user can interact with.
 *
 * ImageSprites <i>extend</i> Sprite, which means that anything you can do with a Sprite, you can also do with an
 * ImageSprite, plus some extra stuff that is specific to ImageSprites.
 *
 * @see Sprite
 */
public abstract class ImageSprite extends Sprite<ImageSprite> {

    public String image;

    protected ImageSprite() {

        targetStyle = TargetStyle.Circle;
    }

    public ImageSprite setImage(String newImage) {

        image = newImage;
        return this;
    }

    @Override
    public String toString() {

        return String.format("ImageSprite: image=%s ", image) + super.toString();
    }
}
