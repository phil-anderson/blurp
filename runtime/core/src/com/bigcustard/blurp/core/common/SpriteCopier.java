package com.bigcustard.blurp.core.common;

import com.bigcustard.blurp.model.*;

public class SpriteCopier {

    public static void copy(Sprite source, Sprite target) {

        target.transparency = source.transparency;
        target.targetStyle = source.targetStyle;
        target.colour = source.colour;
        target.hidden = source.hidden;
        target.angle = source.angle;
        target.scaleX = source.scaleX;
        target.scaleY = source.scaleY;
        target.mouseEnterHandler = source.mouseEnterHandler;
        target.mouseExitHandler = source.mouseExitHandler;
        target.clickHandler = source.clickHandler;
        target.dragHandler = source.dragHandler;
        target.dragReleasedHandler = source.dragReleasedHandler;
        target.mousePressedHandler = source.mousePressedHandler;
        target.mouseReleasedHandler = source.mouseReleasedHandler;
        target.x = source.x;
        target.y = source.y;
    }
}
