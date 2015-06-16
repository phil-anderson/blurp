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

public abstract class Screen {

    /**
     * The background colour of the Screen. Blurp will completely fill the Screen with this colour before it does
     * anything else. You'd think that would mean that changing the background colour at a later stage would cover up
     * anything we'd already displayed on the screen, but Blurp's far too clever to let that happen, and makes sure it
     * all works nicely.
     */
    public Colour backgroundColour = Colours.Black;

    public final Viewport viewport;

    protected Screen(Viewport viewport) {

        this.viewport = viewport;
    }

    /**
     * This method is the beating heart of Blurp. It's responsible for telling Blurp to work it's magic, and it keeps
     * everything running smoothly. Without it, your program will do nothing.
     * <p>
     * Just like a heart, it has to keep on beating, and it's you're responsibility as a Blurp programmer to
     * make that happen by calling this method regularly. Like a heart, you cant make it beat too quickly either.
     * Sounds really tricky eh? Well luckily, there a (relatively) simple rule that will ensure everything runs
     * smoothly...
     * <p><strong>
     *     Any loop that waits for input, or that needs to update the screen each time around should call blurpify once
     *     (and only once) as the last thing it does.
     * </strong><p>
     * That's it. Read it a couple more times to make sure it sinks in.
     * <p>
     * If you're interested, here's what this  incredibly important method does...
     * <p>
     * It takes all of the Blurp objects, drawing commands and graphical settings in your program, and draws them onto
     * the screen. This is called Rendering.
     * <p>
     * It updates all of your Blurp objects based on any effects and velocities you may have given them.
     * <p>
     * It checks all the inputs and triggers any input events
     * <p>
     * It makes sure that your program works at a constant speed by syncing with the framerate of the screen,
     * which is really important. It does this by waiting for the screen to to finish displaying the current frame. On a
     * typical screen, this happens 60 times a second.
     */
    public abstract boolean update();

    public abstract Screen setTitle(String title);
}


