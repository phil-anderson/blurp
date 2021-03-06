package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.core.common.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.events.*;
import com.bigcustard.blurp.util.*;

public class TextSpriteImpl extends TextSprite implements EffectContainer {

    private boolean runningEffect;
    private FalseTrueLatch justOverlapped = new FalseTrueLatch();

    public TextSpriteImpl(String text, double x, double y) {

        this.text = text;
        setPosition(x, y);
        scaleX = 1;
        scaleY = 1;
        transparency = 1;
        colour = Colours.White;
        fontSize = 30;
        wrapWidth = -1;
        justification = Justification.AlignLeft;
        handle = Handle.Center;
    }

    @Override
    public void remove() {

        stopEffects();
        BlurpStore.modelRepository.removeTextSprite(this);
    }

    public Justification getJustification() {

        return justification;
    }

    public double getWrapWidth() {

        return wrapWidth;
    }

    @Override
    public TextSprite move(double angle, double distance) {

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
    public TextSprite runEffect(EffectBase effectToRun, SpriteEventHandler completionHandler, ExistingEffectStrategy existingEffectStrategy) {

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
    public TextSprite pushToBack() {

        BlurpStore.runtimeRepository.registerCommand(new SetZOrderCommand(this, 0));
        return this;
    }

    @Override
    public TextSprite pullToFront() {

        BlurpStore.runtimeRepository.registerCommand(new SetZOrderCommand(this, Integer.MAX_VALUE));
        return this;
    }

    @Override
    public TextSprite pushBehind(Sprite otherSprite) {

        BlurpStore.runtimeRepository.registerCommand(new ChangeZOrderCommand(this, otherSprite, 0));
        return this;
    }

    @Override
    public TextSprite pullInFrontOf(Sprite otherSprite) {

        BlurpStore.runtimeRepository.registerCommand(new ChangeZOrderCommand(this, otherSprite, 1));
        return this;
    }

    @Override
    public TextSprite copy() {

        TextSprite copy = BlurpStore.resources.createTextSprite(this.text);
        SpriteCopier.copy(this, copy);
        return copy;
    }

}
