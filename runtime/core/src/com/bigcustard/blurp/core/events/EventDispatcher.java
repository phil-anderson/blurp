package com.bigcustard.blurp.core.events;

import com.bigcustard.blurp.core.*;

public class EventDispatcher {

    private final SpriteMouseEventDispatcher spriteMouseEventDispatcher;

    public EventDispatcher() {

        spriteMouseEventDispatcher = new SpriteMouseEventDispatcher();
    }

    public void dispatchEvents() {

        spriteMouseEventDispatcher.dispatchEvents();
        BlurpStore.timer.dispatchEvents();
        BlurpStore.keyboard.dispatchEvents();
    }
}
