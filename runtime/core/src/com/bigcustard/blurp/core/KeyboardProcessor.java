package com.bigcustard.blurp.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.*;

public class KeyboardProcessor extends InputAdapter {

    public char lastKeyTyped;
    public IntArray lastKeysPressed = new IntArray();
    public IntArray lastKeysReleased = new IntArray();

    @Override
    public boolean keyTyped(char character) {

        lastKeyTyped = character;
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {

        lastKeysPressed.add(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        lastKeysReleased.add(keycode);
        return false;
    }

    public void clear() {

        lastKeyTyped = 0;
        lastKeysPressed.clear();
        lastKeysReleased.clear();
    }
}
