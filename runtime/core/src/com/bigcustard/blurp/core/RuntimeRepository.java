package com.bigcustard.blurp.core;

import java.util.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.runtimemodel.*;

/**
 * Holds the individual object types in individual maps so that they can be synced or rendered in ordered groups. For
 * example, Images should be handled before ImageSprites as ImageSprites have a dependency on Images.
 *
 * Can do a bulk sync of all model objects to runtime objects.
 */
public class RuntimeRepository {

    private final ModelToRuntimeObjectMap<Image, RuntimeImage> runtimeImages;
    private final ModelToRuntimeObjectMap<ImageSprite, RuntimeImageSprite> runtimeImageSprites;
    private final ModelToRuntimeObjectMap<TextSprite, RuntimeTextSprite> runtimeTextSprites;

    private final List<CommandVisitable> commandRequests;
    private final CommandExecutor commandExecutor;

    public RuntimeRepository() {

        runtimeImages = new ModelToRuntimeObjectMap<Image, RuntimeImage>(RuntimeImage.class);
        runtimeImageSprites = new ModelToRuntimeObjectMap<ImageSprite, RuntimeImageSprite>(RuntimeImageSprite.class);
        runtimeTextSprites = new ModelToRuntimeObjectMap<TextSprite, RuntimeTextSprite>(RuntimeTextSprite.class);

        commandRequests = new ArrayList<CommandVisitable>();
        commandExecutor = new CommandExecutor();
    }

    public void syncWithModelRepository(float deltaTime) {

        // First run any commands that the model has registered requests for.
        commandExecutor.executeAll(commandRequests, deltaTime);
        commandRequests.clear();

        // Then sync the various model object types
        BlurpStore.syncSingletons();
        runtimeImages.syncAll(BlurpStore.modelRepository.getImages());
        runtimeImageSprites.syncAll(BlurpStore.modelRepository.getImageSprites());
        runtimeTextSprites.syncAll(BlurpStore.modelRepository.getTextSprites());
    }

    public RuntimeImage getImage(Image modelImage) {

        return runtimeImages.get(modelImage);
    }

    public RuntimeImageSprite getImageSprite(ImageSprite modelImageSprite) {

        return runtimeImageSprites.get(modelImageSprite);
    }

    public RuntimeTextSprite getTextSprite(TextSprite modelTextSprite) {

        return runtimeTextSprites.get(modelTextSprite);
    }

    // TODO: Would love to get rid of the instanceof usages
    public RuntimeSprite getSprite(Sprite modelSprite) {

        if(modelSprite instanceof ImageSprite) return getImageSprite((ImageSprite) modelSprite);
        if(modelSprite instanceof TextSprite) return getTextSprite((TextSprite) modelSprite);
        return null;
    }

    public void registerCommand(CommandVisitable command) {

        commandRequests.add(command);
    }

    public List<CommandVisitable> getCommandRequests() {

        return commandRequests;
    }

    public void dispose() {

        for(RuntimeImage image : runtimeImages) {
            image.dispose();
        }
        runtimeImages.clear();
        runtimeImageSprites.clear();
        runtimeTextSprites.clear();
        commandRequests.clear();
    }


    /**
     * Deprecated as test purposes only
     */
    @Deprecated
    CommandExecutor getCommandExecutor() {

        return commandExecutor;
    }
}

