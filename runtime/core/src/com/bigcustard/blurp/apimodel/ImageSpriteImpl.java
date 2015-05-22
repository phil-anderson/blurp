package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.core.common.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.events.*;

public class ImageSpriteImpl extends ImageSprite implements EffectContainer {

    private boolean runningEffect;

    public ImageSpriteImpl(Image image, double x, double y) {

        this.image = image;
        this.position(x, y);
        this.scaleX = 1;
        this.scaleY = 1;
        this.alpha = 1;
    }

    @Override
    public void remove() {

        BlurpStore.modelRepository.removeImageSprite(this);
    }

    @Override
    public ImageSprite moveTowards(double targetX, double targetY, double distanceToMove) {

        MoveTowardsHandler.moveTowards(this, targetX, targetY, distanceToMove);
        return this;
    }

    @Override
    public boolean collidedWith(Sprite other) {

        return CollisionDetector.detectCollision(this, other);
    }

    @Override
    public ImageSprite runEffect(EffectBase effectToRun, SpriteEventHandler completionHandler, ExistingEffectStrategy existingEffectStrategy) {

        if(runningEffect && existingEffectStrategy == ExistingEffectStrategy.DoNotRun) {
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
    public boolean isRunningEffect() {

        return this.runningEffect;
    }

    @Override
    public ImageSprite pushToBack() {

        BlurpStore.runtimeRepository.registerCommand(new SetZOrderCommand(this, 0));
        return this;
    }

    @Override
    public ImageSprite pullToFront() {

        BlurpStore.runtimeRepository.registerCommand(new SetZOrderCommand(this, Integer.MAX_VALUE));
        return this;
    }

    @Override
    public ImageSprite pushBehind(Sprite otherSprite) {

        BlurpStore.runtimeRepository.registerCommand(new ChangeZOrderCommand(this, otherSprite, -1));
        return this;
    }

    @Override
    public ImageSprite pullInFrontOf(Sprite otherSprite) {

        BlurpStore.runtimeRepository.registerCommand(new ChangeZOrderCommand(this, otherSprite, 1));
        return this;
    }
}
