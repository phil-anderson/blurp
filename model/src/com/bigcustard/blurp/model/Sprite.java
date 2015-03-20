package com.bigcustard.blurp.model;

/**
 * A sprite is an object that can be put on the screen, moved around, rotated, and generally mucked about with in
 * various ways. For more details, check the subclasses.
 */
public abstract class Sprite<T> {

    private static int instantiationOrder = 0;

    public int zOrder;

    /**
     * The position of the sprite in your Blurp {@link World World}, or to be more
     * precise, the position of the <i>center</i> of the sprite in your Blurp World
     * <p>
     * The bottom-left of the world is at x=0, y=0, and the top-right will be determined by the height and width of the
     * world you created. The default world (i.e what you get if you don;t define one yourself) is 800 wide and 480
     * high.
     * <p>
     * You can happily set the coordinates to be outside of the world coordinates, and Blurp won't bat an eyelid.
     * <p>
     * Typically, the whole world is shown on screen, however more advanced programs sometimes use the
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
     * There are 360 degrees in a circle, so setting this to 360 is the same as 0.
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

    protected Sprite() {

        zOrder = instantiationOrder++;
    }

    /**
     * Rotates the sprite around its center by the specified amount. This is cumulative, which means that if I call it
     * 10 times with a value of 5 (for example) then it'll end up rotated by 50 degrees.
     *
     * @param degrees Degrees to rotate by. See {@link #rotation} for more details.
     * @return Itself.
     */
    public T rotate(double degrees) {

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
     * @return Itself.
     */
    public T scale(double factor) {

        scaleX = factor;
        scaleY = factor;
        return (T) this;
    }
}
