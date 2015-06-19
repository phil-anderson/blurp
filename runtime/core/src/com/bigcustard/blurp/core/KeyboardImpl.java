package com.bigcustard.blurp.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.*;
import com.bigcustard.blurp.model.*;

public class KeyboardImpl extends Keyboard {

    public static KeyboardProcessor KEYBOARD_PROCESSOR = new KeyboardProcessor();

    private IntArray lastKeysPressed;
    private IntArray lastKeysReleased;
    private IntMap<Runnable> keyPressedActions = new IntMap<Runnable>();
    private IntMap<Runnable> keyReleasedActions = new IntMap<Runnable>();

    @Override
    public boolean isKeyPressed(Key key) {

        return Gdx.input.isKeyPressed(key.keyCode);
    }

    @Override
    public boolean wasKeyJustPressed(Key key) {

        return lastKeysPressed.contains(key.keyCode);
    }

    @Override
    public boolean wasKeyJustReleased(Key key) {

        return lastKeysReleased.contains(key.keyCode);
    }

    @Override
    public Keyboard onKeyPressed(Key key, Runnable action) {

        keyPressedActions.put(key.keyCode, action);
        return this;
    }

    @Override
    public Keyboard onKeyReleased(Key key, Runnable action) {

        keyReleasedActions.put(key.keyCode, action);
        return this;
    }

    public void sync() {

        typedCharacter = KEYBOARD_PROCESSOR.lastKeyTyped;
        lastKeysPressed = new IntArray(KEYBOARD_PROCESSOR.lastKeysPressed);
        lastKeysReleased = new IntArray(KEYBOARD_PROCESSOR.lastKeysReleased);

        KEYBOARD_PROCESSOR.clear();
    }

    public void dispatchEvents() {

        for(int keycode : lastKeysPressed.items) {
            if(keyPressedActions.containsKey(keycode)) {
                keyPressedActions.get(keycode).run();
            }
        }
        for(int keycode : lastKeysReleased.items) {
            if(keyReleasedActions.containsKey(keycode)) {
                keyReleasedActions.get(keycode).run();
            }
        }
    }

    @Override
    public String toString() {

        return "Keyboard";
    }
}
