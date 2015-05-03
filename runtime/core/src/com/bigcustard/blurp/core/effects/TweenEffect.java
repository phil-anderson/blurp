package com.bigcustard.blurp.core.effects;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.model.*;

public class TweenEffect extends EffectImpl<TweenEffect> {

    private final int tweenType;
    private final float duration;
    private final boolean relative;
    private final float[] targetValues;

    public TweenEffect(int tweenType, float duration, boolean relative, float... targetValue) {

        this.tweenType = tweenType;
        this.duration = duration;
        this.relative = relative;
        this.targetValues = targetValue;
    }

    @Override
    public BaseTween getTween(Sprite sprite) {

        Tween tween = Tween.to(sprite, tweenType, duration);
        if(relative) {
            tween.targetRelative(targetValues);
        } else {
            tween.target(targetValues);
        }
        populateTween(tween);
        return tween;
    }

    @Override
    protected TweenEffect copy(TweenEffect effect) {

        TweenEffect copy = new TweenEffect(tweenType, duration, relative, targetValues);
        copyBasePropertiesTo(copy);
        return copy;
    }
}
