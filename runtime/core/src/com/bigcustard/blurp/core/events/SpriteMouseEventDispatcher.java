package com.bigcustard.blurp.core.events;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.events.*;

public class SpriteMouseEventDispatcher {

    public void dispatchEvents() {

        for(Sprite sprite : BlurpStore.modelRepository.getSprites()) {
            dispatchEvent(sprite.mouse.entered, sprite.onMouseEnter, sprite);
            dispatchEvent(sprite.mouse.exited, sprite.onMouseExit, sprite);
            dispatchEvent(sprite.mouse.wasClicked, sprite.onClick, sprite);
            dispatchEvent(sprite.mouse.isDragging, sprite.onDrag, sprite);
            dispatchEvent(sprite.mouse.wasDragReleased, sprite.onDragRelease, sprite);
            dispatchEvent(sprite.mouse.wasPressed, sprite.onMousePress, sprite);
            dispatchEvent(sprite.mouse.wasReleased, sprite.onMouseRelease, sprite);
        }
    }

    private void dispatchEvent(boolean condition, SpriteEventHandler handler, Sprite sprite) {

        if(condition && handler != null) {
            handler.handle(sprite);
        }
    }
}
