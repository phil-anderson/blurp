package com.bigcustard.blurp.core.common;

import com.bigcustard.blurp.model.*;

public class SpriteCopier {

    public static void copy(Sprite source, Sprite target) {

        target.transparency = source.transparency;
        target.collisionShape = source.collisionShape;
        target.colour = source.colour;
        target.hidden = source.hidden;
        target.angle = source.angle;
        target.scaleX = source.scaleX;
        target.scaleY = source.scaleY;
        target.whenMouseEnters = source.whenMouseEnters;
        target.whenMouseLeaves = source.whenMouseLeaves;
        target.whenClicked = source.whenClicked;
        target.whenBeingDragged = source.whenBeingDragged;
        target.whenDragReleased = source.whenDragReleased;
        target.whenMousePressed = source.whenMousePressed;
        target.whenMouseReleased = source.whenMouseReleased;
        target.x = source.x;
        target.y = source.y;
    }
}
