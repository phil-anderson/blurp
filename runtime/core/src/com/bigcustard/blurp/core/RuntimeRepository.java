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

    private static RuntimeRepository instance;

    private final ModelRepositoryWrapper modelRepository;
    private final ModelToRuntimeObjectMap<Image, RuntimeImage> runtimeImages;
    private final ModelToRuntimeObjectMap<ImageSprite, RuntimeImageSprite> runtimeImageSprites;
    private final Blurpifier blurpifier;

    private RuntimeRepository() {

        modelRepository = new ModelRepositoryWrapper();
        runtimeImages = new ModelToRuntimeObjectMap<Image, RuntimeImage>(Image.class, RuntimeImage.class);
        runtimeImageSprites = new ModelToRuntimeObjectMap<ImageSprite, RuntimeImageSprite>(ImageSprite.class, RuntimeImageSprite.class);
        blurpifier = new Blurpifier();

        modelRepository.setBlurpifier(blurpifier);
    }

    public static synchronized RuntimeRepository getInstance() {

        if(instance == null) {
            instance = new RuntimeRepository();
        }
        return instance;
    }

    public void syncWithModelRepository() {

        // Sync the various object types
        runtimeImages.syncAll(modelRepository.getImages());
        runtimeImageSprites.syncAll(modelRepository.getImageSprites());
    }

    public RuntimeImage getImage(Image modelImage) {

        return runtimeImages.get(modelImage);
    }

    public RuntimeImageSprite getImageSprite(ImageSprite modelImageSprite) {

        return runtimeImageSprites.get(modelImageSprite);
    }

    public Blurpifier getBlurpifier() {

        return blurpifier;
    }

    public Backdrop getBackdrop() {

        return modelRepository.getBackdrop();
    }
}

