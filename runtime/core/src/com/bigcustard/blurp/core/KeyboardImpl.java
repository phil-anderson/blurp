package com.bigcustard.blurp.core;

import com.badlogic.gdx.*;
import com.bigcustard.blurp.model.*;

public class KeyboardImpl implements Keyboard {

    @Override
    public boolean isKeyPressed(int keyCode) {

        return Gdx.input.isKeyPressed(keyCode);
    }

    @Override
    public boolean isKeyJustPressed(int keyCode) {

        return Gdx.input.isKeyJustPressed(keyCode);
    }
}
