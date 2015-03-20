package com.bigcustard.blurp.model;

/**
 * Yes... That's Color with a 'U'. That's how we spell it in the UK. Live with it.
 * <p>
 * All colours on a computer screen are made up of a blend of red, green and blue. You adjust the blend by specifying
 * the red, green and blue components as numbers between 0 and 1, where 0 is no colour and 1 is full on.
 * <p>
 * Here's some examples...
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;A colour with red = 0, green = 0 and blue = 0 would be black because it has no red, no green and no blue i.e. no
 * colour at all.<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;A colour of red = 0.5, green = 0 and blue = 0.5 would be purple, because it's a mix of red and blue.<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;A colour of red = 1, green = 0 and blue = 0.5 would be a more red-cloloured purple, because it's a mix of red and
 * blue, but with twice more red than blue.
 * <p>
 * You get the picture.
 * <p>
 * Colours can't be changed once you've created them, The higher the numbers are in the colour, the brighter the colour will be.
 */
public class Colour {

    public static final Colour BLACK = new Colour(0, 0, 0);

    /**
     * The red, green and blue components of the colour. See the {@link Colour} class documentation for more details
     */
    public final double red, green, blue;

    /**
     * Create a new colour using the red, green and blue values specified. See the {@link Colour} class
     * documentation for more details
     */
    public Colour(double red, double green, double blue) {

        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Returns a new colour based on this colour mixed with the colour specified.
     */
    public Colour mixedWith(Colour otherColour) {

        return new Colour((red + otherColour.red) / 2.0,
                          (green + otherColour.green) / 2.0,
                          (blue + otherColour.blue) / 2.0);
    }

    /**
     * Returns a new Colour based on this colour but with it's brightess changes.
     *
     * @param factor The brightness factor. Values greater than 1 will make the colour brighter, whereas values less
     *               than 1 will make it darker. It's a multiplier, so for example a brightness factor of 2 will return
     *               a colour twice as bright, whereas a factor of 0.5 will be half as bright.
     * @return
     */
    public Colour withBrightness(double factor) {

        return new Colour(red * factor, green * factor, blue * factor);
    }
}
