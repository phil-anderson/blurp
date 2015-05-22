package com.bigcustard.blurp.runtimemodel;

import com.badlogic.gdx.graphics.g2d.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class RuntimeImageSprite extends RuntimeSprite<ImageSprite> {

    private RuntimeImage image;

    @Override
    public void sync(ImageSprite modelImageSprite, boolean newInstance) {

        super.sync(modelImageSprite, newInstance);

        image = BlurpStore.runtimeRepository.getImage(modelImageSprite.image);

        int width = image.getTextureRegion().getRegionWidth();
        int height = image.getTextureRegion().getRegionHeight();
        setSize(width, height);

        setOrigin(width / 2, height / 2);
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
