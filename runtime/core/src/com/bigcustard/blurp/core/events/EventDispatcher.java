package com.bigcustard.blurp.core.events;

import com.bigcustard.blurp.core.*;

public class EventDispatcher {

    private final SpriteMouseEventDispatcher spriteMouseEventDispatcher;
    private final SpriteCollisionEventDispatcher spriteCollisionEventDispatcher;

    public EventDispatcher() {

        spriteMouseEventDispatcher = new SpriteMouseEventDispatcher();
        spriteCollisionEventDispatcher = new SpriteCollisionEventDispatcher();
    }

    public void dispatchEvents() {

        spriteCollisionEventDispatcher.dispatchEvents();
        spriteMouseEventDispatcher.dispatchEvents();
        BlurpStore.timer.dispatchEvents();
        BlurpStore.keyboard.dispatchEvents();
    }
}
