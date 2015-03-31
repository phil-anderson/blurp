package com.bigcustard.blurp.model;

import com.bigcustard.blurp.core.*;

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



        // TODO: Need to work out width and height (lord alone knows how) so I can expose the pin.




        MSS.getModelRepository().addImage(this);
    }

    /**
     * Removes the Image completely from Blurp. It will be destroyed, and no longer be usable. You should only remove
     * Images when you are sure they are no longer used. Any dependent Blurp objects - e.g. ImageSprites - that refer
     * to this Image will stop working correctly if it's removed.
     * <p>
     * That said, removing things from Blurp when you no longer need them is a really good thing to do, as any resources
     * they use up is freed and available for the rest of your program to use.
     */
    public void remove() {

        MSS.getModelRepository().removeImage(this);
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
