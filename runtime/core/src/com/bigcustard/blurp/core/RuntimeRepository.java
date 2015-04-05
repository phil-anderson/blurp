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


    private final ModelRepositoryImpl modelRepository;
    private final ModelToRuntimeObjectMap<Image, RuntimeImage> runtimeImages;
    private final ModelToRuntimeObjectMap<ImageSprite, RuntimeImageSprite> runtimeImageSprites;
    private final CommandExecutor commandExecutor;

    RuntimeRepository(ModelRepositoryImpl modelRepository) {

        this.modelRepository = modelRepository;
        runtimeImages = new ModelToRuntimeObjectMap<Image, RuntimeImage>(Image.class, RuntimeImage.class);
        runtimeImageSprites = new ModelToRuntimeObjectMap<ImageSprite, RuntimeImageSprite>(ImageSprite.class, RuntimeImageSprite.class);

        commandExecutor = new CommandExecutor();
    }

    public void syncWithModelRepository(float deltaTime) {

        // First run any commands that the model has registered requests for.
        commandExecutor.executeAll(modelRepository.getCommandRequests(), deltaTime);
        modelRepository.commandRequestsComplete();

        // Then sync the various model object types
        runtimeImages.syncAll(modelRepository.getImages());
        runtimeImageSprites.syncAll(modelRepository.getImageSprites());
    }

    public RuntimeImage getImage(Image modelImage) {

        return runtimeImages.get(modelImage);
    }

    public RuntimeImageSprite getImageSprite(ImageSprite modelImageSprite) {

        return runtimeImageSprites.get(modelImageSprite);
    }

    public void dispose() {

        for(RuntimeImage image : runtimeImages) {
            image.dispose();
        }
        runtimeImages.clear();
        runtimeImageSprites.clear();
    }
}

