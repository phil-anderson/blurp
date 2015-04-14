package com.bigcustard.blurp.model;

/**
 * In a way, the Screen object represents the computer screen that your Blurp program is running on. You can change its
 * background colour, and any Sprites you create wll get displayed on it.
 * <p>
 * Another way of looking at it though, is that it's more like a blank canvas... A piece of paper that your program can
 * draw on.
 * <p>
 * Either way, it's the surface that your Blurp program will work its magic on.
 */

// TODO: Make this abstract and implement in Runtime
public abstract class Screen {

    /**
     * The background colour of the Screen. Blurp will completely fill the Screen with this colour before it does
     * anything else. You'd think that would mean that changing the background colour at a later stage would cover up
     * anything we'd already displayed on the screen, but Blurp's far too clever to let that happen, and makes sure it
     * all works nicely.
     */
    public Colour backgroundColour = Colours.INSTANCE.black;

    /**
     * The width and height of the Screen. Well... <i>Sort of</i>... Do be strictly accurate, it's the width and height
     * of the coordinate system that your Blurp program is using, which doesn't have to be the same as the pixel width
     * and height of the Screen as it is displayed on your actual computer screen, although it often is.
     * <p>
     * You probably don't need to worry about this, and if you do then you're probably advanced enough to know how to
     * deal with it!
     */
    public final double width, height;

    protected Screen(double width, double height) {

        this.width = width;
        this.height = height;
    }
}
