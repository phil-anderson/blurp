package com.bigcustard.blurp.runtimemodel;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.Sprite;

public abstract class RuntimeSprite<T extends Sprite> extends Actor implements RuntimeObject<T> {

    protected RuntimeSprite() { }

    @Override
    public void sync(T modelSprite, BlurpObjectProvider blurpObjectProvider, boolean newInstance) {

        setPosition((float) modelSprite.x, (float) modelSprite.y);
        setScale((float) modelSprite.scaleX, (float) modelSprite.scaleY);
        setRotation((float) -modelSprite.rotation);
        setColor((float) modelSprite.colour.red,
                 (float) modelSprite.colour.green,
                 (float) modelSprite.colour.blue,
                 (float) modelSprite.alpha);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
        drawImpl(batch, parentAlpha);
        batch.setColor(1, 1, 1, 1);
    }

    public abstract void drawImpl(Batch batch, float parentAlpha);

    @Override
    public void dispose() {

    }
}
