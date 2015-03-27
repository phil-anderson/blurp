package com.bigcustard.blurp.core;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.runtimemodel.*;

/**
 * Holds the individual object types in individual maps so that they can be synced or rendered in ordered groups. For
 * example, Images should be handled before ImageSprites as ImageSprites have a dependency on Images.
 *
 * Can do a bulk sync of all model objects to runtime objects.
 */
public class RuntimeRepository {


    private final ModelRepositoryWrapper modelRepositoryWrapper;

    private final ModelToRuntimeObjectMap<Image, RuntimeImage> runtimeImages;
    private final ModelToRuntimeObjectMap<ImageSprite, RuntimeImageSprite> runtimeImageSprites;

    RuntimeRepository(ModelRepositoryWrapper modelRepositoryWrapper) {

        this.modelRepositoryWrapper = modelRepositoryWrapper;

        runtimeImages = new ModelToRuntimeObjectMap<Image, RuntimeImage>(Image.class, RuntimeImage.class);
        runtimeImageSprites = new ModelToRuntimeObjectMap<ImageSprite, RuntimeImageSprite>(ImageSprite.class, RuntimeImageSprite.class);
    }

    public void syncWithModelRepository() {

        // Sync the various object types
        runtimeImages.syncAll(modelRepositoryWrapper.getImages());
        runtimeImageSprites.syncAll(modelRepositoryWrapper.getImageSprites());
    }

    public RuntimeImage getImage(Image modelImage) {

        return runtimeImages.get(modelImage);
    }

    public RuntimeImageSprite getImageSprite(ImageSprite modelImageSprite) {

        return runtimeImageSprites.get(modelImageSprite);
    }

    public Canvas getScreen() {

        return modelRepositoryWrapper.getScreen();
    }

    public void dispose() {

        for(RuntimeImage image : runtimeImages) {
            image.dispose();
        }
        runtimeImages.clear();
        runtimeImageSprites.clear();
    }
}

