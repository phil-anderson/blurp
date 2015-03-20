package com.bigcustard.blurp.runtimemodel;

import com.badlogic.gdx.graphics.g2d.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class RuntimeImageSprite extends RuntimeSprite implements RuntimeObject<ImageSprite> {

    private RuntimeImage image;

    public RuntimeImageSprite(ImageSprite modelImageSprite) {

        sync(modelImageSprite);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
    }

    @Override
    public void sync(ImageSprite modelImageSprite) {

        super.sync(modelImageSprite);
        image = RuntimeRepository.getInstance().getImage(modelImageSprite.image);
    }
}
