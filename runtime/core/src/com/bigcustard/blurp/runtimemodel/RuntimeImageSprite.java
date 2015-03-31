package com.bigcustard.blurp.runtimemodel;

import com.badlogic.gdx.graphics.g2d.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class RuntimeImageSprite extends RuntimeSprite<ImageSprite> {

    private RuntimeImage image;

    public RuntimeImageSprite(ImageSprite modelImageSprite) {

        sync(modelImageSprite);

        int width = image.getTextureRegion().getRegionWidth();
        int height = image.getTextureRegion().getRegionHeight();
        setSize(width, height);
        setOrigin(width / 2, height / 2);
        RSS.getBlurpScreen().addActor(this);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        // Draw scaled texture centered at (and rotated around) origin
        batch.draw(image.getTextureRegion(),
                   getX() - getOriginX(), getY() - getOriginY(),
                   getOriginX(), getOriginY(),
                   getWidth(), getHeight(),
                   getScaleX(), getScaleY(),
                   getRotation());

        super.draw(batch, parentAlpha);
    }

    @Override
    public void sync(ImageSprite modelImageSprite) {

        super.sync(modelImageSprite);
        image = RSS.getRuntimeRepository().getImage(modelImageSprite.image);
    }

    @Override
    public void dispose() {

        super.remove();
    }
}
