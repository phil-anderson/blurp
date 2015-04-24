package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.constants.*;

/**
 * Allows you to get the state of the keyboad - assuming the device your program is running on has a keyboard.
 */
public interface Keyboard {

    /**
     * Determines whether the key represented by the specified keyCode is currently held down
     *
     * @param key The {@link com.bigcustard.blurp.model.constants.Key key} to check.
     * @return True if the key is currently being held down by the user.
     */
    public boolean isKeyPressed(Key key);

    /**
     * Determines whether the key represented by the specified keyCode has just been pressed.
     *
     * @param key The {@link com.bigcustard.blurp.model.constants.Key key} to check.
     * @return True if the key has just been pressed by a user
     */
    public boolean isKeyJustPressed(Key key);
}
