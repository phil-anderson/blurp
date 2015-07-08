package com.bigcustard.blurp.runtimemodel;

import java.util.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class RuntimeAnimationSprite extends RuntimeSprite<AnimationSprite> {

    private String[] imageNames;
    private RuntimeImage[] images;

    private int currentFrame;

    @Override
    public void sync(AnimationSprite modelSprite, boolean newInstance) {

        super.sync(modelSprite, newInstance);

        if(!Arrays.equals(modelSprite.images, imageNames)) {
            if(images != null) {
                for(RuntimeImage image : images) {
                    BlurpStore.imageCache.stopUsingImage(image);
                }
            }
            int imageCount = modelSprite.images.length;
            imageNames = Arrays.copyOf(modelSprite.images, imageCount);
            images = new RuntimeImage[imageCount];
            for(int i = 0; i < imageCount; i++) {
                images[i] = BlurpStore.imageCache.useImage(imageNames[i]);
            }
        }

        if(newInstance || currentFrame != modelSprite.currentFrame) {
            if(modelSprite.currentFrame < 0 || modelSprite.currentFrame >= images.length) {
                throw new BlurpException("Invalid frame number: " + modelSprite.currentFrame +
                                         ". Must be between 0 and " + (images.length - 1));
            }
            currentFrame = modelSprite.currentFrame;
            int width = images[currentFrame].getTextureRegion().getRegionWidth();
            int height = images[currentFrame].getTextureRegion().getRegionHeight();
            setSize(width, height);
            setOrigin(width / 2, height / 2);
            updateCollisionShapes();
        }
    }

    @Override
    public void render(Batch batch, float parentAlpha) {

        batch.draw(images[currentFrame].getTextureRegion(), 0, 0);
    }

    @Override
    public void dispose() {

        remove();
        super.dispose();
    }
}
