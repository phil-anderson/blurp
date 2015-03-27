package com.bigcustard.blurp.model;

/**
 * Behind the scenes, your blurp program extends this class, and will have access to the protected properties and
 * methods in it.
 * <p>
 * If nothing else, you should read the documentation on the {@link #blurpify()} method.
 * <p>
 * TEMPORARY - For now, your subclass should implement Runnable.run(). This will change once we establish a solid
 * scripting strategy.
 */
public abstract class BlurpMain implements Runnable {

    /**
     * The Blurp {@link Canvas}, which will be drawn before anything else.
     */
    protected final Canvas canvas;

    /**
     * The {@link BlurpConfig} that can be used to change Blurp's settings
     */
    protected final BlurpConfig config;

    protected BlurpMain() {

        config = new BlurpConfig();
        canvas = ModelRepository.getInstance().getCanvas();
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
    public void blurpify() {

        ModelRepository.getInstance().requestBlurpify();
    }
}
