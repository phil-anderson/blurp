package com.bigcustard.blurp.core;

import com.badlogic.gdx.math.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.util.*;

public class MouseImpl implements Mouse {

    private double x, y;
    private double absoluteX, absoluteY;
    private boolean leftPressed, rightPressed;

    public void sync() {

        Vector3 position = MouseState.getPosition(SpriteLayer.Main);
        this.x = position.x;
        this.y = position.y;

        position = MouseState.getPosition(SpriteLayer.Background);
        this.absoluteX = position.x;
        this.absoluteY = position.y;

        this.leftPressed = MouseState.isLeftPressed();
        this.rightPressed = MouseState.isRightPressed();
    }

    @Override
    public double x() {

        return x;
    }

    @Override
    public double y() {

        return y;
    }

    public double absoluteX() {

        return absoluteX;
    }

    public double absoluteY() {

        return absoluteY;
    }

    @Override
    public boolean isLeftButtonPressed() {

        return leftPressed;
    }

    @Override
    public boolean isRightButtonPressed() {

        return rightPressed;
    }
}