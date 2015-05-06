package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.constants.*;

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
public class Screen {

    /**
     * The background colour of the Screen. Blurp will completely fill the Screen with this colour before it does
     * anything else. You'd think that would mean that changing the background colour at a later stage would cover up
     * anything we'd already displayed on the screen, but Blurp's far too clever to let that happen, and makes sure it
     * all works nicely.
     */
    public Colour backgroundColour = Colours.BLACK;
}
