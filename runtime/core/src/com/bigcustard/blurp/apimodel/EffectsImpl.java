package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.effects.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public class EffectsImpl extends Effects {

    @Override
    public Effect moveTo(double newX, double newY, double duration) {

        return new TweenEffect(TweenTypes.MOVE, (float) duration, false, (float) newX, (float) newY);
    }

    @Override
    public Effect moveBy(double changeInX, double changeInY, double duration) {

        return new TweenEffect(TweenTypes.MOVE, (float) duration, true, (float) changeInX, (float) changeInY);
    }

    @Override
    public Effect rotateTo(double newAngle, double duration) {

        return new TweenEffect(TweenTypes.ROTATE, (float) duration, false, (float) newAngle);
    }

    @Override
    public Effect rotateBy(double changeInAngle, double duration) {

        return new TweenEffect(TweenTypes.ROTATE, (float) duration, true, (float) changeInAngle);
    }

    @Override
    public Effect scale(double newScaleX, double newScaleY, double duration) {

        return new TweenEffect(TweenTypes.SCALE, (float) duration, false, (float) newScaleX, (float) newScaleY);
    }

    @Override
    public Effect alpha(double newAlpha, double duration) {

        return new TweenEffect(TweenTypes.ALPHA, (float) duration, false, (float) newAlpha);
    }

    @Override
    public Effect colour(Colour newColour, double duration) {

        return new TweenEffect(TweenTypes.COLOUR, (float) duration, false, (float) newColour.red, (float) newColour.green, (float) newColour.blue);
    }

    @Override
    public Effect combine(Effect firstEffect, Effect secondEffect, Effect... otherEffects) {

        Effect[] effectsToCombine = buildEffectsArray(firstEffect, secondEffect, otherEffects);
        return new ParallelCompoundEffect(effectsToCombine);
    }

    @Override
    public Effect sequence(Effect firstEffect, Effect secondEffect, Effect... otherEffects) {

        Effect[] effectsToDoInSequence = buildEffectsArray(firstEffect, secondEffect, otherEffects);
        return new SequentialCompoundEffect(effectsToDoInSequence);
    }

    private Effect[] buildEffectsArray(Effect firstEffect, Effect secondEffect, Effect[] otherEffects) {

        Effect[] effectsToCombine = new Effect[otherEffects.length + 2];
        effectsToCombine[0] = firstEffect;
        effectsToCombine[1] = secondEffect;
        System.arraycopy(otherEffects, 0, effectsToCombine, 2, otherEffects.length);
        return effectsToCombine;
    }
}
