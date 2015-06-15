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
        target.onMouseEnter = source.onMouseEnter;
        target.onMouseExit = source.onMouseExit;
        target.onClick = source.onClick;
        target.onDrag = source.onDrag;
        target.onDragRelease = source.onDragRelease;
        target.onMousePress = source.onMousePress;
        target.onMouseRelease = source.onMouseRelease;
        target.x = source.x;
        target.y = source.y;
    }
}
