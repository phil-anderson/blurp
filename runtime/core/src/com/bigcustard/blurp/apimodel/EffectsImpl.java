package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.effects.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;

public class EffectsImpl extends Effects {

    public EffectsImpl() {

        defaultDuration = 1000;
        defaultStyle = EffectStyle.SmoothStartStop;
    }

    @Override
    public Effect moveTo(double newX, double newY) {

        return new TweenEffect(TweenTypes.MOVE, false, (float) newX, (float) newY);
    }

    @Override
    public Effect moveBy(double changeInX, double changeInY) {

        return new TweenEffect(TweenTypes.MOVE, true, (float) changeInX, (float) changeInY);
    }

    @Override
    public Effect rotateTo(double newAngle) {

        return new TweenEffect(TweenTypes.ROTATE, false, (float) newAngle);
    }

    @Override
    public Effect rotateBy(double changeInAngle) {

        return new TweenEffect(TweenTypes.ROTATE, true, (float) changeInAngle);
    }

    @Override
    public Effect scaleTo(double newScaleX, double newScaleY) {

        return new TweenEffect(TweenTypes.SCALE, false, (float) newScaleX, (float) newScaleY);
    }

    @Override
    public Effect scaleBy(double newScaleX, double newScaleY) {

        return new TweenEffect(TweenTypes.SCALE, true, (float) newScaleX, (float) newScaleY);
    }

    @Override
    public Effect transparency(double newTransparency) {

        return new TweenEffect(TweenTypes.ALPHA, false, (float) newTransparency);
    }

    @Override
    public Effect colour(Colour newColour) {

        return new TweenEffect(TweenTypes.COLOUR, false, (float) newColour.red, (float) newColour.green, (float) newColour.blue);
    }

    @Override
    public EffectGroup combine(EffectBase... effectsToCombine) {

        return new ParallelEffectGroup(effectsToCombine);
    }

    @Override
    public EffectGroup sequence(EffectBase... effectsToDoInSequence) {

        return new SequentialEffectGroup(effectsToDoInSequence);
    }

    public void reset() {

        defaultDuration = 1000;
        defaultStyle = EffectStyle.SmoothStartStop;
    }
}
