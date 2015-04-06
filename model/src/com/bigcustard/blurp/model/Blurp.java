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
public abstract class Blurp {

    /**
     * A selection of preset colours
     */
    public final Colours colours = Colours.INSTANCE;

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
    public abstract void blurpify();

    // TODO: Javadoc for factory methods

    public abstract Image image(String filename);

    /**
     * Constructs a new ImageSprite using the image file specified, and places it at the center of the Screen.
     * <p>
     * A new Image will be created just for this ImageSprite, and the ImageSprite's image property will be set
     * accordingly.
     * <p>
     * If you want more than one ImageSprite to share the same image, then you should create a single {@link Image}
     * object for the image and construct all of the ImageSprites using that instead. It'll be more efficient as the
     * image will only be loaded once.
     *
     * @param imageFilename The name of the image file to load
     */
    public abstract ImageSprite imageSprite(String imageFilename);

    /**
     * Constructs a new ImageSprite using the image file specified, and places it at the X and Y coordinates specified.
     * A new Image will be created just for this ImageSprite, and the image property will be set accordingly.
     * <p>
     * If you want more than one ImageSprite to share the same image, then you should create a single {@link Image}
     * object for the image and construct all of the ImageSprites using that instead. It'll be more efficient as the
     * image will only be loaded once.
     *
     * @param imageFilename The name of the image file to load
     * @param x The X coordinate
     * @param y The Y coordinate
     */
    public abstract ImageSprite imageSprite(String imageFilename, double x, double y);

    public abstract ImageSprite imageSprite(Image image);

    public abstract ImageSprite imageSprite(Image image, double x, double y);

    public abstract Colour colour(double red, double green, double blue);
}
