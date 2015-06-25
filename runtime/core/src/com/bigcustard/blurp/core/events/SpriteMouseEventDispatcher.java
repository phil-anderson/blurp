package com.bigcustard.blurp.core.events;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.events.*;

public class SpriteMouseEventDispatcher {

    public void dispatchEvents() {

        for(Sprite sprite : BlurpStore.modelRepository.getSprites()) {
            dispatchEvent(sprite.mouse.entered, sprite.mouseEnterHandler, sprite);
            dispatchEvent(sprite.mouse.exited, sprite.mouseExitHandler, sprite);
            dispatchEvent(sprite.mouse.wasClicked, sprite.clickHandler, sprite);
            dispatchEvent(sprite.mouse.isDragging, sprite.dragHandler, sprite);
            dispatchEvent(sprite.mouse.wasDragReleased, sprite.dragReleasedHandler, sprite);
            dispatchEvent(sprite.mouse.wasPressed, sprite.mousePressedHandler, sprite);
            dispatchEvent(sprite.mouse.wasReleased, sprite.mouseReleasedHandler, sprite);
        }
    }

    private void dispatchEvent(boolean condition, SpriteEventHandler handler, Sprite sprite) {

        if(condition && handler != null) {
            handler.handle(sprite);
        }
    }
}
