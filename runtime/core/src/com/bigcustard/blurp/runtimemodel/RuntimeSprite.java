package com.bigcustard.blurp.runtimemodel;

import com.badlogic.gdx.scenes.scene2d.*;
import com.bigcustard.blurp.model.*;

public class RuntimeSprite extends Actor {

    public void sync(Sprite modelSprite) {

        setPosition((float) modelSprite.x, (float) modelSprite.y);

        setScale((float) modelSprite.scaleX, (float) modelSprite.scaleY);

        setRotation((float) modelSprite.rotation);

        setColor(1, 1, 1, (float) modelSprite.alpha);
    }
}
