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

    private final ModelToRuntimeObjectMap<ImageSprite, RuntimeImageSprite> runtimeImageSprites;
    private final ModelToRuntimeObjectMap<AnimationSprite, RuntimeAnimationSprite> runtimeAnimationSprites;
    private final ModelToRuntimeObjectMap<TextSprite, RuntimeTextSprite> runtimeTextSprites;

    private final List<Command> commands;
    private final List<Command> deferredCommands;

    public RuntimeRepository() {

        runtimeImageSprites = new ModelToRuntimeObjectMap<ImageSprite, RuntimeImageSprite>(RuntimeImageSprite.class);
        runtimeAnimationSprites = new ModelToRuntimeObjectMap<AnimationSprite, RuntimeAnimationSprite>(RuntimeAnimationSprite.class);
        runtimeTextSprites = new ModelToRuntimeObjectMap<TextSprite, RuntimeTextSprite>(RuntimeTextSprite.class);

        commands = new ArrayList<Command>();
        deferredCommands = new ArrayList<Command>();
    }

    public void syncWithModelRepository(float deltaTime) {

        // First run any commands that the model has registered requests for.
        executeCommands(commands, deltaTime);
        commands.clear();

        // Then sync the various model object types
        BlurpStore.syncSingletons();
        runtimeImageSprites.syncAll(BlurpStore.modelRepository.getImageSprites());
        runtimeAnimationSprites.syncAll(BlurpStore.modelRepository.getAnimationSprites());
        runtimeTextSprites.syncAll(BlurpStore.modelRepository.getTextSprites());

        for(Sprite modelSprite : BlurpStore.modelRepository.pullCreatedSprites()) {
            BlurpStore.blurpScreen.addActor(getSprite(modelSprite));
        }

        // Finally, run any commands that were deferred
        executeCommands(deferredCommands, deltaTime);
        commands.clear();
    }

    public RuntimeImageSprite getImageSprite(ImageSprite modelImageSprite) {

        return runtimeImageSprites.get(modelImageSprite);
    }

    private RuntimeSprite getAnimationSprite(AnimationSprite modelAnimationSprite) {

        return runtimeAnimationSprites.get(modelAnimationSprite);
    }

    public RuntimeTextSprite getTextSprite(TextSprite modelTextSprite) {

        return runtimeTextSprites.get(modelTextSprite);
    }

    // TODO: Would love to get rid of the instanceof usages
    public RuntimeSprite getSprite(Sprite modelSprite) {

        if(modelSprite instanceof ImageSprite) return getImageSprite((ImageSprite) modelSprite);
        if(modelSprite instanceof AnimationSprite) return getAnimationSprite((AnimationSprite) modelSprite);
        if(modelSprite instanceof TextSprite) return getTextSprite((TextSprite) modelSprite);
        return null;
    }

    public void registerCommand(Command command) {

        commands.add(command);
    }

    public void deferCommand(Command command) {

        deferredCommands.add(command);
    }

    public List<Command> getCommands() {

        return commands;
    }

    public void dispose() {

        runtimeImageSprites.clear();
        runtimeTextSprites.clear();

        commands.clear();
        deferredCommands.clear();
    }

    public void executeCommands(List<Command> commands, float delta) {

        for(Command command : commands) {
            command.execute(delta);
        }
    }
}

