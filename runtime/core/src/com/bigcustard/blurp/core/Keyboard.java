package com.bigcustard.blurp.core;

import com.badlogic.gdx.*;
import com.bigcustard.blurp.model.*;

public class Keyboard implements IKeyboard {


    @Override
    public boolean isKeyDown(Key key) {

        return Gdx.input.isKeyPressed(key.getKeyCode());
    }

    @Override
    public boolean isKeyJustPressed(Key key) {

        return Gdx.input.isKeyJustPressed(key.getKeyCode());
    }
}
