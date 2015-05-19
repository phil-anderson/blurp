package com.bigcustard.blurp.util;

import com.badlogic.gdx.*;
import com.badlogic.gdx.math.*;
import com.bigcustard.blurp.core.*;

public class MouseState {

    // Flyweight
    private static Vector3 mouseXY = new Vector3();

    public static Vector3 getPosition() {

        mouseXY.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        return BlurpStore.gdxCamera.unproject(mouseXY);
    }

    public static boolean isLeftPressed() {

        return Gdx.input.isButtonPressed(Input.Buttons.LEFT);
    }

    public static boolean isRightPressed() {

        return Gdx.input.isButtonPressed(Input.Buttons.RIGHT);
    }
}
