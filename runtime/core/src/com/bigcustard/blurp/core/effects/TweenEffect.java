package com.bigcustard.blurp.core.effects;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.apimodel.*;

public class TweenEffect extends EffectImpl implements TweenFactory {

    private final int tweenType;
    private final int duration;
    private final boolean relative;
    private final float[] targetValues;

    public TweenEffect(int tweenType, int duration, boolean relative, float... targetValue) {

        this.tweenType = tweenType;
        this.duration = duration;
        this.relative = relative;
        this.targetValues = targetValue;
    }

    @Override
    public Tween createTween(Object sprite) {

        Tween tween = Tween.to(sprite, tweenType, duration);
        if(relative) {
            tween.targetRelative(targetValues);
        } else {
            tween.target(targetValues);
        }
        populateTween(tween);
        return tween;
    }

}
