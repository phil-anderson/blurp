package com.bigcustard.blurp.model.testutils;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.events.*;

public class SpriteForTests extends Sprite {

    @Override
    public Object moveTowards(double targetX, double targetY, double speed) {

        return null;
    }

    @Override
    public boolean collidedWith(Sprite other) {

        return false;
    }

    @Override
    public Object runEffect(EffectBase effectToRun, SpriteEventHandler onCompletion, ExistingEffectStrategy existingEffectStrategy) {

        return null;
    }

    @Override
    public void remove() {

    }

    @Override
    public boolean isRunningEffect() {

        return false;
    }

    @Override
    public Object stopEffect() {

        return null;
    }

    @Override
    public Object pushToBack() {

        return null;
    }

    @Override
    public Object pullToFront() {

        return null;
    }

    @Override
    public Object pushBehind(Sprite otherSprite) {

        return null;
    }

    @Override
    public Object pullInFrontOf(Sprite otherSprite) {

        return null;
    }

    @Override
    public Object copy() {

        return null;
    }
}
