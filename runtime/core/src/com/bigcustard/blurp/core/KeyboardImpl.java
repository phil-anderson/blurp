package com.bigcustard.blurp.core;

import com.badlogic.gdx.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;

public class KeyboardImpl extends Keyboard {

    public static KeyboardProcessor KEYBOARD_PROCESSOR = new KeyboardProcessor();

    @Override
    public boolean isKeyPressed(Key key) {

        return Gdx.input.isKeyPressed(key.keyCode);
    }

    @Override
    public boolean wasKeyJustPressed(Key key) {

        return Gdx.input.isKeyJustPressed(key.keyCode);
    }

    public void sync() {

        typedKey = KEYBOARD_PROCESSOR.pullLastKeyTyped();
    }

    public static class KeyboardProcessor extends InputAdapter {

        private char rawKeyTyped;

        @Override
        public boolean keyTyped(char character) {

            rawKeyTyped = character;
            return false;
        }

        public char pullLastKeyTyped() {

            char result = rawKeyTyped;
            rawKeyTyped = 0;
            return result;
        }
    }
}
