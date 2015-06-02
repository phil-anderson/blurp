package com.bigcustard.blurp.util;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.constants.*;

public class MouseState {

    public static Vector3 getPosition(SpriteLayer layer) {

        OrthographicCamera camera = layer == null || layer == SpriteLayer.Main ? BlurpStore.mainCamera : BlurpStore.staticCamera;

        Vector3 mouseXY = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        return camera.unproject(mouseXY);
    }

    public static boolean isLeftPressed() {

        return Gdx.input.isButtonPressed(Input.Buttons.LEFT);
    }

    public static boolean isRightPressed() {

        return Gdx.input.isButtonPressed(Input.Buttons.RIGHT);
    }

    public static boolean isInsideWindow() {

        return BlurpStore.mouseWindowChecker.isInsideWindow();
    }
}
