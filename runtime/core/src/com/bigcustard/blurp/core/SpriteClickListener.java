package com.bigcustard.blurp.core;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.bigcustard.blurp.model.*;

public class SpriteClickListener extends ClickListener {

    private Sprite target;

    private boolean dragging;
    private boolean clicked;
    private boolean dragReleased;
    private int clickCount;

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {

        dragging = true;
        super.touchDragged(event, x, y, pointer);
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

        SpriteMouseState result = new SpriteMouseState(isOver(), isPressed(), dragging, clicked, dragReleased, clickCount);
        clicked = false;
        dragReleased = false;
        clickCount = 0;
        return result;
    }
}
