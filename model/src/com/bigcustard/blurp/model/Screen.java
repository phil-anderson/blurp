package com.bigcustard.blurp.model;

/**
 * In a way, the Screen object represents the physical screen that your Blurp program is running on. You can change its
 * background colour, and any Sprites you create wll get displayed on it.
 * <p>
 * Another way of looking at it though, is that it's more like a blank piece of paper that your program can draw on.
 * <p>
 * Either way, it's the surface that your Blurp program will work its magic on.
 */
public class Screen {

    /**
     * The background colour of the screen. Blurp will completely fill the Screen with this colour before it does
     * anything else. You'd think that would mean that changing the background colour at a later stage would cover up
     * anything else we'd displayed on the screen snce then, but Blurp's far too clever to let that happen, and makes
     * sure it all works nicely.
     */
    public Colour backgroundColour = Colour.BLACK;

    /**
     * The width and height of the screen. Well... <i>Sort of</i>... Do be strictly accurate, it's actually the width
     * and height of the coordinate system that your Blurp program is using, which doesn't have to be the same as the
     * pixel width and heght of the screen that your Blurp program is running on - although it quite often is.
     * <p>
     * You probably don;t need to worry about this, and if you do, you're probably advance enought to know how to deal
     * with it.
     * <p>
     * You can set the width and height you want to use through the {@link BlurpMain#initialise initialise method}
     */
    public final int width, height;

    Screen(int width, int height) {

        this.width = width;
        this.height = height;
    }
}
