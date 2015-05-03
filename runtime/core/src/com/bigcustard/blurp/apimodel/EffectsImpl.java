package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.effects.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public class EffectsImpl extends Effects {

    @Override
    public Effect moveTo(double newX, double newY, int duration) {

        return new TweenEffect(TweenTypes.MOVE, duration, false, (float) newX, (float) newY);
    }

    @Override
    public Effect moveBy(double changeInX, double changeInY, int duration) {

        return new TweenEffect(TweenTypes.MOVE, duration, true, (float) changeInX, (float) changeInY);
    }

    @Override
    public Effect rotateTo(double newAngle, int duration) {

        return new TweenEffect(TweenTypes.ROTATE, duration, false, (float) newAngle);
    }

    @Override
    public Effect rotateBy(double changeInAngle, int duration) {

        return new TweenEffect(TweenTypes.ROTATE, duration, true, (float) changeInAngle);
    }

    @Override
    public Effect scale(double newScaleX, double newScaleY, int duration) {

        return new TweenEffect(TweenTypes.SCALE, duration, false, (float) newScaleX, (float) newScaleY);
    }

    @Override
    public Effect alpha(double newAlpha, int duration) {

        return new TweenEffect(TweenTypes.ALPHA, duration, false, (float) newAlpha);
    }

    @Override
    public Effect colour(Colour newColour, int duration) {

        return new TweenEffect(TweenTypes.COLOUR, duration, false, (float) newColour.red, (float) newColour.green, (float) newColour.blue);
    }

    @Override
    public BaseEffect pause(int duration) {

        return new PauseEffect(duration);
    }

    @Override
    public Effect combine(BaseEffect firstEffect, BaseEffect secondEffect, BaseEffect... otherEffects) {

        BaseEffect[] effectsToCombine = buildEffectsArray(firstEffect, secondEffect, otherEffects);
        return new ParallelCompoundEffect(effectsToCombine);
    }

    @Override
    public Effect sequence(BaseEffect firstEffect, BaseEffect secondEffect, BaseEffect... otherEffects) {

        BaseEffect[] effectsToDoInSequence = buildEffectsArray(firstEffect, secondEffect, otherEffects);
        return new SequentialCompoundEffect(effectsToDoInSequence);
    }

    private BaseEffect[] buildEffectsArray(BaseEffect firstEffect, BaseEffect secondEffect, BaseEffect[] otherEffects) {

        BaseEffect[] effectsToCombine = new BaseEffect[otherEffects.length + 2];
        effectsToCombine[0] = firstEffect;
        effectsToCombine[1] = secondEffect;
        System.arraycopy(otherEffects, 0, effectsToCombine, 2, otherEffects.length);
        return effectsToCombine;
    }
}
