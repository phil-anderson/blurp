package com.bigcustard.blurp.core.events;

public class EventDispatcher {

    private final SpriteMouseEventDispatcher spriteMouseEventDispatcher;
    private final TimerEventDispatcher timerEventDispatcher;

    public EventDispatcher() {

        spriteMouseEventDispatcher = new SpriteMouseEventDispatcher();
        timerEventDispatcher = new TimerEventDispatcher();
    }

    public void dispatchEvents() {

        spriteMouseEventDispatcher.dispatchEvents();
        timerEventDispatcher.dispatchEvents();
    }
}
