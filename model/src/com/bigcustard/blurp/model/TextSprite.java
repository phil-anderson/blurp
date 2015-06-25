package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.constants.*;

public abstract class TextSprite extends Sprite<TextSprite> {

    public String text;

    public double fontSize;

    public double wrapWidth;

    public Handle handle;

    public Justification justification;

    public boolean colourTagsEnabled;

    public TextSprite setText(String newText) {

        text = newText;
        targetStyle = TargetStyle.Rectangle;
        return this;
    }

    public TextSprite setFontSize(double newFontSize) {

        fontSize = newFontSize;
        return this;
    }

    public TextSprite setWrapWidth(double newWrapWidth) {

        wrapWidth = newWrapWidth;
        return this;
    }

    public TextSprite setHandle(Handle newHandle) {

        handle = newHandle;
        return this;
    }

    public TextSprite setJustification(Justification newJustification) {

        justification = newJustification;
        return this;
    }

    public TextSprite setColourTagsEnabled(boolean colourTagsEnabled) {

        this.colourTagsEnabled = colourTagsEnabled;
        return this;
    }

    public TextSprite setWrap(double width, Justification justification) {

        wrapWidth = width;
        this.justification = justification;
        return this;
    }

    @Override
    public String toString() {

        return String.format("TextSprite: text=%s fontSize=%.2f wrapWidth=%.1f handle=%s justification=%s colourTagsEnabled=%s ",
                             text, fontSize, wrapWidth, handle, justification, colourTagsEnabled) + super.toString();
    }
}
