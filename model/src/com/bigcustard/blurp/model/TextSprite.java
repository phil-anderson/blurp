package com.bigcustard.blurp.model;

public abstract class TextSprite extends Sprite<TextSprite> {

    // TODO: Support Markup, which is actually in libgdx!! See TextMarkup class.
    public String text;

    public abstract TextSprite anchorLeft();

    public abstract TextSprite anchorCenter();

    public abstract TextSprite anchorRight();

    public abstract TextSprite anchorTop();

    public abstract TextSprite anchorMiddle();

    public abstract TextSprite anchorBottom();

    public abstract TextSprite justifyLeft(double width);

    public abstract TextSprite justifyCenter(double width);

    public abstract TextSprite justifyRight(double width);

    /**
     * Removes the TextSprite completely from Blurp. It will be destroyed, and no longer appear on screen.
     * <p>
     * Removing things from Blurp when you no longer need them is a really good thing to do, as any resources they use
     * up is freed and available for the rest of your program to use.
     */
    public abstract void remove();
}
