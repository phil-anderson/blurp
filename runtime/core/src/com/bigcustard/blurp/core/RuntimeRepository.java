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

    private final List<SoundEffectImpl> soundEffects;
    private final List<MusicImpl> musicInstances;
    private final List<MusicImpl> heldMusic;

    private final List<Command> commands;
    private final List<Command> deferredCommands;

    public RuntimeRepository() {

        runtimeImageSprites = new ModelToRuntimeObjectMap<ImageSprite, RuntimeImageSprite>(RuntimeImageSprite.class);
        runtimeAnimationSprites = new ModelToRuntimeObjectMap<AnimationSprite, RuntimeAnimationSprite>(RuntimeAnimationSprite.class);
        runtimeTextSprites = new ModelToRuntimeObjectMap<TextSprite, RuntimeTextSprite>(RuntimeTextSprite.class);
        musicInstances = new ArrayList<MusicImpl>();
        heldMusic = new ArrayList<MusicImpl>();
        soundEffects = new ArrayList<SoundEffectImpl>();

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

    public void registerMusic(MusicImpl music) {

        musicInstances.add(music);
    }

    public void deregisterMusic(Music music) {

        musicInstances.remove(music);
    }

    public void registerSoundEffect(SoundEffectImpl soundEffect) {

        soundEffects.add(soundEffect);
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

    public void executeCommands(List<Command> commands, float delta) {

        for(Command command : commands) {
            command.execute(delta);
        }
    }

    public void pauseAudio() {

        for(SoundEffectImpl sound : soundEffects) {
            sound.getGdxSound().pause();
        }

        for(MusicImpl music : musicInstances) {
            if(music.getGdxMusic().isPlaying()) {
                music.pause();
                heldMusic.add(music);
            }
        }
    }

    public void resumeAudio() {

        for(SoundEffectImpl sound : soundEffects) {
            sound.getGdxSound().resume();
        }

        for(MusicImpl music : heldMusic) {
            music.play();
        }
        heldMusic.clear();
    }

    public void dispose() {

        runtimeImageSprites.clear();
        runtimeTextSprites.clear();
        runtimeAnimationSprites.clear();
        disposeAudio();

        commands.clear();
        deferredCommands.clear();
    }

    public void disposeAudio() {

        for(MusicImpl music : musicInstances) {
            music.dispose();
        }
        musicInstances.clear();
        heldMusic.clear();

        for(SoundEffectImpl soundEffect: soundEffects) {
            soundEffect.dispose();
        }
        soundEffects.clear();
    }
}

