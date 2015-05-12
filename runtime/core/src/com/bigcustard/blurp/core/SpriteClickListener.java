package com.bigcustard.blurp.core;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.bigcustard.blurp.model.*;

public class SpriteClickListener extends ClickListener {

    private Sprite target;

    private boolean touched;
    private boolean dragging;
    private double dragX, dragY;
    private boolean justClicked;
    private boolean doubleClicked;
    private boolean mouseOver;

    public void reset() {

        justClicked = false;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

        touched = true;
        return true;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {

        dragging = true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

        touched = false;
        dragging = false;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {

        mouseOver = true;
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {

        mouseOver = false;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {

        justClicked = true;
    }
}
