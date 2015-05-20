package com.bigcustard.blurp.core.events;

import com.bigcustard.blurp.core.*;

public class EventDispatcher {

    public static void dispatchEvents() {

        SpriteMouseEventDispatcher.dispatchEvents(BlurpStore.modelRepository.getSprites());
    }

}
