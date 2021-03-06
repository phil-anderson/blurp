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
     * Returns a new Colour based on this colour but with it's brightness changed.
     *
     * @param factor The brightness factor. Values greater than 1 will make the colour brighter, whereas values less
     *               than 1 will make it darker. It's a multiplier, so for example a brightness factor of 2 will return
     *               a colour twice as bright, whereas a factor of 0.5 will be half as bright.
     */
    public Colour withBrightness(double factor) {

        return new Colour(Math.min(red * factor, 1),
                          Math.min(green * factor, 1),
                          Math.min(blue * factor, 1));
    }

    @Override
    /**
     * Tests if this Colour is the same as another (i.e. it has exactly the same red, green and blue components.
     */
    public boolean equals(Object other) {

        if(this == other) return true;
        if(other == null || getClass() != other.getClass()) return false;

        Colour otherColour = (Colour) other;

        if(Double.compare(otherColour.blue, blue) != 0) return false;
        if(Double.compare(otherColour.green, green) != 0) return false;
        if(Double.compare(otherColour.red, red) != 0) return false;

        return true;
    }

    @Override
    // Generated by IntelliJ
    /**
     * This produces a hashcode of the Colour, which is like a special number based on the red, green and blue values.
     * It's very unlikely that you'd use this method directly, but if you use this Colour in a HashSet, or as the key in
     * a HashMap, then this method will be used behind the scenes.
     */
    public int hashCode() {

        int result;
        long temp;
        temp = Double.doubleToLongBits(red);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(green);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(blue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {

        return String.format("Colour: Red=%.3f, Green=%.3f, Blue=%.3f", red, green, blue);
    }
}
