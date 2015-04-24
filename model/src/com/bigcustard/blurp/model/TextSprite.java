package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.constants.*;

public abstract class TextSprite extends Sprite<TextSprite> {

    // TODO: Support Markup, which is actually in libgdx!! See TextMarkup class.
    public String text;

    public double fontSize;

    public double wrapWidth;

    public Handle handle;

    public Justification justification;

    public boolean enableColorTags;

    public abstract TextSprite wrap(double width, Justification justification);

    /**
     * Removes the TextSprite completely from Blurp. It will be destroyed, and no longer appear on screen.
     * <p>
     * Removing things from Blurp when you no longer need them is a really good thing to do, as any resources they use
     * up is freed and available for the rest of your program to use.
     */
    public abstract void remove();
}
