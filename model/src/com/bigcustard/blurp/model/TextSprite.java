package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.constants.*;

public abstract class TextSprite extends Sprite<TextSprite> {

    public String text;

    public double fontSize;

    public double wrapWidth;

    public Handle handle;

    public Justification justification;

    public boolean colourTagsEnabled;

    public TextSprite text(String newText) {

        text = newText;
        return this;
    }

    public TextSprite fontSize(double newFontSize) {

        fontSize = newFontSize;
        return this;
    }

    public TextSprite wrapWidth(double newWrapWidth) {

        wrapWidth = newWrapWidth;
        return this;
    }

    public TextSprite handle(Handle newHandle) {

        handle = newHandle;
        return this;
    }

    public TextSprite justification(Justification newJustification) {

        justification = newJustification;
        return this;
    }

    public TextSprite colourTagsEnabled(boolean colourTagsEnabled) {

        this.colourTagsEnabled = colourTagsEnabled;
        return this;
    }

    public TextSprite wrap(double width, Justification justification) {

        wrapWidth = width;
        this.justification = justification;
        return this;
    }

    /**
     * Removes the TextSprite completely from Blurp. It will be destroyed, and no longer appear on screen.
     * <p/>
     * Removing things from Blurp when you no longer need them is a really good thing to do, as any resources they use
     * up is freed and available for the rest of your program to use.
     */
    public abstract void remove();
}
