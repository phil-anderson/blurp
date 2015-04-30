package com.bigcustard.blurp.runtimemodel;

import com.badlogic.gdx.graphics.g2d.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class RuntimeImageSprite extends RuntimeSprite<ImageSprite> {

    private RuntimeImage image;

    @Override
    public void sync(ImageSprite modelImageSprite, BlurpObjectProvider blurpObjectProvider, boolean newInstance) {

        super.sync(modelImageSprite, blurpObjectProvider, newInstance);

        image = blurpObjectProvider.getRuntimeRepository().getImage(modelImageSprite.image);

        int width = image.getTextureRegion().getRegionWidth();
        int height = image.getTextureRegion().getRegionHeight();
        setSize(width, height);

        setOrigin(width / 2, height / 2);

        if(newInstance) {
            blurpObjectProvider.getBlurpScreen().addActor(this);
        }

        updateCollisionShapes();
    }

    @Override
    public void render(Batch batch, float parentAlpha) {

            batch.draw(image.getTextureRegion(), 0, 0);
    }

    @Override
    public void dispose() {

        remove();
    }
}
