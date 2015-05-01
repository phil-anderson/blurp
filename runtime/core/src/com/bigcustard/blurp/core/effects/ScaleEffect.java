package com.bigcustard.blurp.core.effects;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.apimodel.*;

public class ScaleEffect extends EffectImpl implements TweenFactory {

    private final float targetScale;
    private final int duration;

    public ScaleEffect(double targetScale, int duration) {

        this.targetScale = (float) targetScale;
        this.duration = duration;
    }

    @Override
    public Tween createTween(Object sprite) {

        Tween tween = Tween.to(sprite, TweenTypes.SCALE, duration).target(targetScale);
        populateTween(tween);
        return tween;
    }
}
