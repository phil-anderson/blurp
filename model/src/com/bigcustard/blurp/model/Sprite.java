package com.bigcustard.blurp.model;

/**
 * A sprite is an object that can be put on the screen, moved around, rotated, and generally mucked about with in
 * various ways. For more details, check the subclasses.
 */

@SuppressWarnings("unchecked")
public abstract class Sprite<T> {

    /**
     * The position of the sprite, or to be more precise, the position of the <i>center</i> of the sprite.
     * <p>
     * The bottom-left of the world is at x=0, y=0, and the top-right will be determined by the height and width of the
     * world you created. The default world (i.e what you get if you don;t define one yourself) is 800 wide and 480
     * high.
     * <p>
     * You can happily set the coordinates to be outside of the world coordinates, and Blurp won't bat an eyelid.
     * <p>
     * Typically, the whole world is shown on screen, however more advanced programs sometimes use the
     *
     * {@link Camera Camera} class to zoom into an area and pan around the world.
     */
    public double x, y;

    /**
     * The scale of the sprite in the x and y axes. Typically you'd want to set both of these to the same value to
     * make the sprite larger or smaller, which can be done more easily by using the {@link #scale} convenience method.
     * <p>
     * If you set them individually though, some cool effects can be achieved as only the x or y dimension will be
     * stretched or shrunk. For example, if your sprite was of a rubber ball, then shrinking the y scale a little, and
     * growing the x scale will make it look like it's being squished.
     * </p>
     * Values less than 1 will make the sprite smaller, whereas values greater than 1 will make it grow. Exactly 1 means
     * normal size.
     * <p>
     * Negative values will flip the sprite in that axis as well as growing it or shrinking it.
     * </p>
     */
    public double scaleX, scaleY;

    /**
     * The angle that the sprite is rotated by. A positive value is clockwise, whereas a negative one is anti-clockwise
     * (which is UK English for "counter-clockwise" in case you didn't know).
     * <p>
     * There are 360 degrees in a circle, so setting this to 360 is the same as setting it to 0.
     */
    public double rotation;

    /**
     * The alpha transparency of the sprite. This odd phrase actual just means how transparent it is.
     * <p>
     * A value of 1 means that it's not transparent at all and will look completely solid. Lower values will make it
     * look more and more ghostly, until at zero it will be completely transparent and you won't be able to see it at
     * all.
     * <p>
     * Try it out... You'll soon see what it does.
     */
    public double alpha;

    /**
     * This method provides a handy way to set both X and Y coordinates in one hit.
     *
     * @param newX The new X coordinate
     * @param newY The new Y coordinate
     * @return The Sprite.
     */
    public T position(double newX, double newY) {

        x = newX;
        y = newY;
        return (T) this;
    }

    /**
     * Rotates the sprite around its center by the specified amount. This is cumulative, which means that if I call it
     * 10 times with a value of 5 (for example) then it'll end up rotated by 50 degrees.
     *
     * @param degrees Degrees to rotate by. See {@link #rotation} for more details.
     * @return The Sprite.
     */
    public T rotateBy(double degrees) {

        rotation += degrees;
        return (T) this;
    }

    /**
     * Changes the size of the sprite by multiplying its normal size by the factor. Specifying a factor smaller than 1
     * will shrink the sprite whereas a factor greater than 1 will grow it. Exactly 1 will return it to normal size.
     * <p>
     * If the number is negative, then the sprite will also be flipped in both axes - This is the same as rotating it
     * 180 degrees. Can you work out why?
     *
     * @param factor The scaling factor to multiply the size by.
     * @return The Sprite.
     */
    public T scale(double factor) {

        scaleX = factor;
        scaleY = factor;
        return (T) this;
    }

    /**
     * Flips the Sprite on the X-axis (i.e. from left-to-right), so it looks like a mirror-image of the original.
     *
     * @return The Sprite.
     */
    public T flipX() {

        scaleX = -scaleX;
        return (T) this;
    }

    /**
     * Flips the Sprite on the Y-axis (i.e. from top-to-bottom), so it looks upside-down.
     *
     * @return The Sprite.
     */
    public T flipY() {

        scaleY = -scaleY;
        return (T) this;
    }

    /**
     * Moves the Sprite towards the specified target coordinates <u>the next time blurpify is called</u>. If you  keep
     * calling this method then eventually it'll reach the target. The speed parameter specified how fast the Sprite
     * should move and therefore how quickly it will reach the target. This method does all the hard work of calculating
     * how far the sprite needs to move in the X and Y dimensions in order to move smoothly, straight towards the
     * target.
     * <p>
     * How much the Sprite will move towards the target depends on the speed parameter, and how much time has passed
     * since the last call to {@link Blurp#blurpify() Blurp.blurpify()}
     *
     * @param targetX The target X coordinate
     * @param targetY The target Y coordinate
     * @param speed The speed in units-per-second that we want the Sprite to move at.
     * @return The Sprite.
     */
    public abstract T moveTowards(double targetX, double targetY, double speed);
}
