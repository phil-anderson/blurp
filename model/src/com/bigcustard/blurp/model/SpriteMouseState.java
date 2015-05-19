package com.bigcustard.blurp.model;

public class SpriteMouseState {

    public static final SpriteMouseState NULL = new SpriteMouseState(false, false, false, false, false, 0);

    public final boolean over;
    public final boolean holding;
    public final boolean dragging;
    public final boolean clicked;
    public final boolean dragReleased;
    public final int clickCount;

    public SpriteMouseState(boolean over, boolean held, boolean dragging, boolean clicked, boolean dragReleased, int clickCount) {

        this.over = over;
        this.holding = held;
        this.dragging = dragging;
        this.clicked = clicked;
        this.dragReleased = dragReleased;
        this.clickCount = clickCount;
    }
}
