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
public abstract class Image {

    /**
     * The name of the image file that this image was created with. This property is read-only.
     */
    public final String name;

    /**
     * This constructor is protected - Use {@link Resources#loadImage} instead.
     */
    protected Image(String name) {

        this.name = name;
    }

    /**
     * Removes the Image completely from Blurp. It will be destroyed, and no longer be usable. You should only remove
     * Images when you are sure they are no longer used. Any dependent Blurp objects - e.g. ImageSprites - that refer
     * to this Image will stop working correctly if it's removed.
     * <p>
     * That said, removing things from Blurp when you no longer need them is a really good thing to do, as any resources
     * they use up is freed and available for the rest of your program to use.
     */
    public abstract void remove();

    @Override
    public String toString() {

        return String.format("Image: name=%s", name);
    }

}
