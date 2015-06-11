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
    private boolean entered;
    private boolean exited;
    private boolean pressed;
    private boolean released;
    private boolean clicked;
    private boolean dragReleased;
    private int clickCount;
    private double dragOffsetX, dragOffsetY;

    public SpriteClickListener(RuntimeSprite sprite) {

        this.sprite = sprite;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

        // Prevents enter being set on a touchDown.
        entered = !MouseState.isLeftPressed() && !MouseState.isRightPressed();
        super.enter(event, x, y, pointer, fromActor);
        event.handle();
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

        // TODO: Hacky workaround for LibGdx bug where clicking frequently fires an exit event - see Stage.act() method.
        if(event.getTarget() != event.getRelatedActor()) {
            exited = true;
            super.exit(event, x, y, pointer, toActor);
        }
        event.handle();
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {

        dragging = true;
        super.touchDragged(event, x, y, pointer);
        event.handle();
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

        pressed = true;
        Vector3 mouseXY = MouseState.getPosition(sprite.getLayer()).cpy();
        dragOffsetX = sprite.getX() - mouseXY.x;
        dragOffsetY = sprite.getY() - mouseXY.y;
        return super.touchDown(event, x, y, pointer, button);
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

        released = true;
        dragReleased = dragging;
        dragging = false;
        super.touchUp(event, x, y, pointer, button);
        event.handle();
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {

        clicked = !dragReleased;
        clickCount = getTapCount();
        event.handle();
    }

    public SpriteMouseState buildState() {

        Vector3 mouseXY = MouseState.getPosition(sprite.getLayer()).cpy();
        SpriteMouseState result = new SpriteMouseState(isOver(), isPressed(), dragging, entered, exited, pressed,
                                                       released, clicked, dragReleased,
                                                       clickCount, mouseXY.x + dragOffsetX, mouseXY.y + dragOffsetY);
        entered = false;
        exited = false;
        pressed = false;
        released = false;
        clicked = false;
        dragReleased = false;
        clickCount = 0;
        return result;
    }
}
