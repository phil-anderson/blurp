package com.bigcustard.blurp.model;

public class SpriteMouseState {

    public static final SpriteMouseState NULL = new SpriteMouseState(false, false, false, false, false, false, false, false, false, 0, 0, 0);

    public final boolean over;
    public final boolean holding;
    public final boolean dragging;
    public final boolean mouseEntered;
    public final boolean mouseLeft;
    public final boolean mousePressed;
    public final boolean mouseReleased;
    public final boolean clicked;
    public final int clickCount;
    public final boolean dragReleased;
    public final double dragX, dragY;

    public SpriteMouseState(boolean over, boolean held, boolean dragging, boolean mouseEntered, boolean mouseLeft, boolean mousePressed, boolean mouseReleased, boolean clicked, boolean dragReleased, int clickCount, double dragX, double dragY) {

        this.over = over;
        this.holding = held;
        this.dragging = dragging;
        this.mouseEntered = mouseEntered;
        this.mouseLeft = mouseLeft;
        this.mousePressed = mousePressed;
        this.mouseReleased = mouseReleased;
        this.clicked = clicked;
        this.dragReleased = dragReleased;
        this.clickCount = clickCount;
        this.dragX = dragX;
        this.dragY = dragY;
    }
}
