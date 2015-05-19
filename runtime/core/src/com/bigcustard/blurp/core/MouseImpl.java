package com.bigcustard.blurp.core;

import com.badlogic.gdx.math.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.util.*;

public class MouseImpl implements Mouse {

    private double x, y;
    private boolean leftPressed, rightPressed;

    public void sync() {

        Vector3 position = MouseState.getPosition();
        this.x = position.x;
        this.y = position.y;
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

    @Override
    public boolean isLeftButtonPressed() {

        return leftPressed;
    }

    @Override
    public boolean isRightButtonPressed() {

        return rightPressed;
    }
}
