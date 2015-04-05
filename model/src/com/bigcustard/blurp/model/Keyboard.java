package com.bigcustard.blurp.model;

/**
 * Allows you to get the state of the keyboad - assuming the device your program is running on has a keyboard.
 */
public interface Keyboard {

    /**
     * Determines whether the specified key is currently held down
     *
     * @param key {@link Key} to check
     * @return True if the key is currently being held down by the user.
     */
    public boolean isKeyDown(Key key);

    /**
     * Determines whether the specified key has just been pressed.
     *
     * @param key {@link Key} to check
     * @return True if the key has just been pressed by a user
     */
    public boolean isKeyJustPressed(Key key);
}
