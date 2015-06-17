package com.bigcustard.blurp.core;

import com.badlogic.gdx.math.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.util.*;

public class MouseImpl implements Mouse {

    private double x, y;
    private double absoluteX, absoluteY;
    private boolean leftPressed, rightPressed;
    private boolean insideWindow;

    public void sync() {

        Vector3 position = MouseState.getPosition(ScreenLayer.Main);
        this.x = position.x;
        this.y = position.y;

        position = MouseState.getPosition(ScreenLayer.Background);
        this.absoluteX = position.x;
        this.absoluteY = position.y;

        this.leftPressed = MouseState.isLeftPressed();
        this.rightPressed = MouseState.isRightPressed();
        this.insideWindow = MouseState.isInsideWindow();
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

    @Override
    public boolean isInsideWindow() {

        return insideWindow;
    }

    @Override
    public String toString() {

        return String.format("Mouse: x=%.1f y=%.1f absoluteX=%.1f absoluteY=%.1f leftPressed=%s rightPressed=%s",
                             x, y, absoluteX, absoluteY, leftPressed, rightPressed);
    }
}
