package com.bigcustard.blurp.core;

import com.badlogic.gdx.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;

public class KeyboardImpl implements Keyboard {

    @Override
    public boolean isKeyPressed(Keys key) {

        return Gdx.input.isKeyPressed(key.keyCode);
    }

    @Override
    public boolean isKeyJustPressed(Keys key) {

        return Gdx.input.isKeyJustPressed(key.keyCode);
    }
}
