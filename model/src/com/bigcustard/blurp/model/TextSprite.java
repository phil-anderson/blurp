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
        collisionShape = CollisionShape.BoundaryRectangle;
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
}
