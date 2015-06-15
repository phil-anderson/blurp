package com.bigcustard.blurp.util;

import com.badlogic.gdx.*;
import com.badlogic.gdx.math.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.constants.*;

public class MouseState {

    public static Vector3 getPosition(ScreenLayer layer) {

        // Find static position and clamp it to viewport
        Vector3 position = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        BlurpStore.staticViewport.unproject(position);

        position.x = MathUtils.clamp(position.x, 0, BlurpStore.staticViewport.getWorldWidth() - 1);
        position.y = MathUtils.clamp(position.y, 0, BlurpStore.staticViewport.getWorldHeight() - 1);

        // If a layer is static (background or overlay), then we're done.
        if(layer != null && layer != ScreenLayer.Main) {
            return position;
        }

        // Unproject clamped value to give us clamped screen coords
        BlurpStore.staticViewport.project(position);

        // Screen is y-down. Unproject handles this, but project doesn't. This appears to be by design. Really!?!!
        position.y = BlurpStore.staticViewport.getScreenHeight() - position.y - 1;

        // Finally we can unproject those into main coords
        return BlurpStore.mainViewport.unproject(position);
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
