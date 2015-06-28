package com.bigcustard.blurp.core.effects;

import java.util.*;
import aurelienribon.tweenengine.*;
import aurelienribon.tweenengine.equations.*;
import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.util.*;

public class TweenEffect extends EffectImpl<Effect> implements Effect {

    private static final Map<EffectStyle, TweenEquation> EASING_FUNCTIONS = new HashMap<EffectStyle, TweenEquation>();
    static {
        EASING_FUNCTIONS.put(EffectStyle.Linear, Linear.INOUT);
        EASING_FUNCTIONS.put(EffectStyle.SmoothStart, Quad.IN);
        EASING_FUNCTIONS.put(EffectStyle.SmoothStop, Quad.OUT);
        EASING_FUNCTIONS.put(EffectStyle.SmoothStartStop, Quad.INOUT);
        EASING_FUNCTIONS.put(EffectStyle.FastThenSlow, Quart.OUT);
        EASING_FUNCTIONS.put(EffectStyle.SlowThenFast,  Quart.IN);
        EASING_FUNCTIONS.put(EffectStyle.SlowFastSlow, Quart.INOUT);
        EASING_FUNCTIONS.put(EffectStyle.BackStart, Back.IN);
        EASING_FUNCTIONS.put(EffectStyle.BackStop, Back.OUT);
        EASING_FUNCTIONS.put(EffectStyle.BackStartStop, Back.INOUT);
        EASING_FUNCTIONS.put(EffectStyle.BounceStart, Bounce.IN);
        EASING_FUNCTIONS.put(EffectStyle.BounceStop, Bounce.OUT);
        EASING_FUNCTIONS.put(EffectStyle.BounceStartStop, Bounce.INOUT);
        EASING_FUNCTIONS.put(EffectStyle.ElasticStart, Elastic.IN);
        EASING_FUNCTIONS.put(EffectStyle.ElasticStop, Elastic.OUT);
        EASING_FUNCTIONS.put(EffectStyle.ElasticStartStop, Elastic.INOUT);
    }

    private final int tweenType;
    private final boolean relative;
    private final float[] targetValues;

    private int duration;
    private EffectStyle effectStyle;

    public TweenEffect(int tweenType, boolean relative, float... targetValue) {

        this.tweenType = tweenType;
        this.relative = relative;
        this.targetValues = targetValue;
        this.duration = BlurpStore.effects.defaultDuration;
        this.effectStyle = BlurpStore.effects.defaultStyle;
    }

    @Override
    public Effect duration(int durationMillis) {

        TweenEffect copy = (TweenEffect) copy(this);
        copy.duration = durationMillis;
        return copy;
    }

    @Override
    public Effect style(EffectStyle style) {

        TweenEffect copy = (TweenEffect) copy(this);
        copy.effectStyle = style;
        return copy;
    }


    @Override
    public BaseTween getTween(Object target) {

        Tween tween = Tween.to(target, tweenType, duration / 1000f);
        if(relative) {

            // Special case for scaleBy - e.g. scaleBy(2) should double in size, not add 2 to scale.
            if(tweenType == TweenTypes.SCALE) {
                float[] initialScaleValues = Tweens.getTweenAccessorValues(target, TweenTypes.SCALE);
                tween.target(initialScaleValues[0] * targetValues[0],
                             initialScaleValues[1] *= targetValues[1]);
            } else {
                tween.targetRelative(targetValues);
            }
        } else {
            tween.target(targetValues);
        }
        populateTween(tween);
        tween.ease(EASING_FUNCTIONS.get(effectStyle));
        return tween;
    }

    @Override
    protected Effect copy(Effect effect) {

        TweenEffect copy = new TweenEffect(tweenType, relative, targetValues);
        copyBasePropertiesTo(copy);
        copy.duration = this.duration;
        copy.effectStyle = this.effectStyle;
        return copy;
    }
}
