package com.bigcustard.blurp.runtimemodel;

import com.badlogic.gdx.scenes.scene2d.*;
import com.bigcustard.blurp.model.*;

public abstract class RuntimeSprite<T extends Sprite> extends Actor implements RuntimeObject<T> {

    protected RuntimeSprite() {

    }

    @Override
    public void sync(T modelSprite) {

        setPosition((float) modelSprite.x, (float) modelSprite.y);

        setScale((float) modelSprite.scaleX, (float) modelSprite.scaleY);

        setRotation((float) -modelSprite.rotation);

        setColor(1, 1, 1, (float) modelSprite.alpha);
    }

    @Override
    public void dispose() {

    }
}
