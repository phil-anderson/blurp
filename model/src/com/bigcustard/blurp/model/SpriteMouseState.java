package com.bigcustard.blurp.model;

public class SpriteMouseState {

    public final boolean isOver;
    public final boolean isHolding;
    public final boolean isDragging;
    public final boolean entered;
    public final boolean exited;
    public final boolean wasPressed;
    public final boolean wasReleased;
    public final boolean wasClicked;
    public final int clickCount;
    public final boolean wasDragReleased;
    public final double dragX, dragY;

    public SpriteMouseState(boolean isOver, boolean isHolding, boolean isDragging, boolean entered, boolean exited, boolean wasPressed, boolean wasReleased, boolean wasClicked, boolean wasDragReleased, int clickCount, double dragX, double dragY) {

        this.isOver = isOver;
        this.isHolding = isHolding;
        this.isDragging = isDragging;
        this.entered = entered;
        this.exited = exited;
        this.wasPressed = wasPressed;
        this.wasReleased = wasReleased;
        this.wasClicked = wasClicked;
        this.wasDragReleased = wasDragReleased;
        this.clickCount = clickCount;
        this.dragX = dragX;
        this.dragY = dragY;
    }

    @Override
    public String toString() {

        return String.format("SpriteMouseState : isOver=%s isHolding=%s isDragging=%s entered=%s exited=%s wasPressed=%s wasReleased=%s wasClicked=%s clickCount=%d wasDragReleased=%s dragX=%.1f dragY=%.1f",
                   isOver, isHolding, isDragging, entered, exited, wasPressed, wasReleased, wasClicked, clickCount, wasDragReleased, dragX, dragY);
    }
}
