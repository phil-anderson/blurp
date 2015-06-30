package com.bigcustard.blurp.core.events;

import java.util.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.events.*;

public class SpriteCollisionEventDispatcher {

    public void dispatchEvents() {

        for(Sprite sprite : BlurpStore.modelRepository.getSprites()) {
            // TODO: Why is this an unchecked cast!?!! The HashMap is correctly typed.
            for(Map.Entry<Sprite, SpriteEventHandler> entry : (Set<Map.Entry<Sprite, SpriteEventHandler>>) sprite.collisionEventHandlers.entrySet()) {
                if(sprite.overlaps(entry.getKey())) {
                    entry.getValue().handle(sprite);
                }
            }
        }
    }
}
