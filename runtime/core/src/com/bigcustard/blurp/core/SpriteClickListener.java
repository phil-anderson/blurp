package com.bigcustard.blurp.core;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.runtimemodel.*;
import com.bigcustard.blurp.util.*;

public class SpriteClickListener extends ClickListener {

    private final RuntimeSprite sprite;
    private boolean dragging;
    private boolean clicked;
    private boolean dragReleased;
    private int clickCount;
    private double dragOffsetX, dragOffsetY;

    public SpriteClickListener(RuntimeSprite sprite) {

        this.sprite = sprite;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {

        dragging = true;
        super.touchDragged(event, x, y, pointer);
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

        Vector3 mouseXY = MouseState.getPosition().cpy();
        dragOffsetX = sprite.getX() - mouseXY.x;
        dragOffsetY = sprite.getY() - mouseXY.y;
        return super.touchDown(event, x, y, pointer, button);
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

        dragReleased = dragging;
        dragging = false;
        super.touchUp(event, x, y, pointer, button);
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {

        clicked = !dragReleased;
        clickCount = getTapCount();
    }

    public SpriteMouseState buildState() {

        Vector3 mouseXY = MouseState.getPosition().cpy();
        SpriteMouseState result = new SpriteMouseState(isOver(), isPressed(), dragging, clicked, dragReleased, clickCount,
                                                       mouseXY.x + dragOffsetX, mouseXY.y + dragOffsetY);
        clicked = false;
        dragReleased = false;
        clickCount = 0;
        return result;
    }
}
