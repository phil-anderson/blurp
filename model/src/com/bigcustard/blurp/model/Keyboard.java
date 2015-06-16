package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.constants.*;

/**
 * Allows you to get the state of the keyboad - assuming the device your program is running on has a keyboard.
 */
public abstract class Keyboard {

    public char typedKey = 0;

    /**
     * Determines whether the key represented by the specified keyCode is currently held down
     *
     * @param key The {@link com.bigcustard.blurp.model.constants.Key key} to check.
     * @return True if the key is currently being held down by the user.
     */
    public abstract boolean isKeyPressed(Key key);

    /**
     * Determines whether the key represented by the specified keyCode has just been pressed.
     *
     * @param key The {@link com.bigcustard.blurp.model.constants.Key key} to check.
     * @return True if the key has just been pressed by a user
     */
    public abstract boolean wasKeyJustPressed(Key key);

    public abstract boolean wasKeyJustReleased(Key key);

    public abstract Keyboard onKeyPressed(Key key, Runnable action);

    public abstract Keyboard onKeyReleased(Key key, Runnable action);

    public boolean wasKeyTyped() {

        return typedKey != 0;
    }
}
