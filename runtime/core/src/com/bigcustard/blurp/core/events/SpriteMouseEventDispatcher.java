package com.bigcustard.blurp.core.events;

import java.util.*;
import com.bigcustard.blurp.model.*;

public class SpriteMouseEventDispatcher {

    public static void dispatchEvents(List<Sprite> sprites) {

        for(Sprite sprite : sprites) {

            dispatchEvent(sprite.mouseState.mouseEntered, sprite.whenMouseEnters, sprite);
            dispatchEvent(sprite.mouseState.mouseLeft, sprite.whenMouseLeaves, sprite);
            dispatchEvent(sprite.mouseState.clicked, sprite.whenClicked, sprite);
            dispatchEvent(sprite.mouseState.dragging, sprite.whenBeingDragged, sprite);
            dispatchEvent(sprite.mouseState.dragReleased, sprite.whenDragReleased, sprite);
            dispatchEvent(sprite.mouseState.mousePressed, sprite.whenMousePressed, sprite);
            dispatchEvent(sprite.mouseState.mouseReleased, sprite.whenMouseReleased, sprite);
        }
    }

    private static void dispatchEvent(boolean condition, SpriteEventHandler handler, Sprite sprite) {

        if(condition && handler != null) {
            handler.handle(sprite);
        }
    }
}
