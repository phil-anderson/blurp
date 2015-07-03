package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.core.common.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.events.*;
import com.bigcustard.blurp.util.*;

public class AnimationSpriteImpl extends AnimationSprite implements EffectContainer {

    private boolean runningEffect;
    private FalseTrueLatch justOverlapped = new FalseTrueLatch();
    private boolean playing;

    private Runnable stepFrame = new Runnable() {
        @Override
        public void run() {
            step();
        }
    };

    public AnimationSpriteImpl(double x, double y, String... images) {

        this.setPosition(x, y);
        this.scaleX = 1;
        this.scaleY = 1;
        this.transparency = 1;
        this.images = images;
    }

    @Override
    public AnimationSprite play(int frameDuration) {

        BlurpStore.timer.every(frameDuration, stepFrame);
        playing = true;
        return this;
    }

    @Override
    public AnimationSprite stop() {

        BlurpStore.timer.remove(stepFrame);
        playing = false;
        return this;
    }

    @Override
    public boolean isPlaying() {

        return playing;
    }

    @Override
    public void remove() {

        stopEffects();
        BlurpStore.modelRepository.removeAnimationSprite(this);
    }

    @Override
    public AnimationSprite moveTowards(double targetX, double targetY, double distanceToMove) {

        MoveTowardsHandler.moveTowards(this, targetX, targetY, distanceToMove);
        return this;
    }

    @Override
    public AnimationSprite move(double angle, double distance) {

        MoveHandler.move(this, angle, distance);
        return this;
    }

    @Override
    public boolean overlaps(Sprite other) {

        return CollisionDetector.detectCollision(this, other);
    }

    @Override
    public boolean collidedWith(Sprite other) {

        return justOverlapped.getValue(overlaps(other));
    }

    @Override
    public AnimationSprite runEffect(EffectBase effectToRun, SpriteEventHandler completionHandler, ExistingEffectStrategy existingEffectStrategy) {

        if(runningEffect && existingEffectStrategy == ExistingEffectStrategy.OnlyIfNoExistingEffect) {
            return this;
        }

        BlurpStore.runtimeRepository.registerCommand(new RunEffectCommand<Sprite>(this, effectToRun, completionHandler, existingEffectStrategy, false));
        runningEffect = effectToRun != null;
        return this;
    }

    @Override
    public void setRunningEffect(boolean running) {

        this.runningEffect = running;
    }

    @Override
    public boolean isRunningAnEffect() {

        return this.runningEffect;
    }

    @Override
    public AnimationSprite pushToBack() {

        BlurpStore.runtimeRepository.registerCommand(new SetZOrderCommand(this, 0));
        return this;
    }

    @Override
    public AnimationSprite pullToFront() {

        BlurpStore.runtimeRepository.registerCommand(new SetZOrderCommand(this, Integer.MAX_VALUE));
        return this;
    }

    @Override
    public AnimationSprite pushBehind(Sprite otherSprite) {

        BlurpStore.runtimeRepository.registerCommand(new ChangeZOrderCommand(this, otherSprite, -1));
        return this;
    }

    @Override
    public AnimationSprite pullInFrontOf(Sprite otherSprite) {

        BlurpStore.runtimeRepository.registerCommand(new ChangeZOrderCommand(this, otherSprite, 1));
        return this;
    }

    @Override
    public AnimationSprite copy() {

        AnimationSprite copy = BlurpStore.resources.createAnimationSprite(this.images);
        SpriteCopier.copy(this, copy);
        return copy;
    }
}
