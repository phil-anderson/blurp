package com.bigcustard.blurp.core.events;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.events.*;

public class SpriteMouseEventDispatcher {

    public void dispatchEvents() {

        for(Sprite sprite : BlurpStore.modelRepository.getSprites()) {
            dispatchEvent(sprite.mouseState.mouseEntered, sprite.whenMouseEnters, sprite);
            dispatchEvent(sprite.mouseState.mouseLeft, sprite.whenMouseLeaves, sprite);
            dispatchEvent(sprite.mouseState.clicked, sprite.whenClicked, sprite);
            dispatchEvent(sprite.mouseState.dragging, sprite.whenBeingDragged, sprite);
            dispatchEvent(sprite.mouseState.dragReleased, sprite.whenDragReleased, sprite);
            dispatchEvent(sprite.mouseState.mousePressed, sprite.whenMousePressed, sprite);
            dispatchEvent(sprite.mouseState.mouseReleased, sprite.whenMouseReleased, sprite);
        }
    }

    private void dispatchEvent(boolean condition, SpriteEventHandler handler, Sprite sprite) {

        if(condition && handler != null) {
            handler.handle(sprite);
        }
    }
}
