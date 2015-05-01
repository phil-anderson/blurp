package com.bigcustard.blurp.apimodel;

import aurelienribon.tweenengine.BaseTween;
import com.bigcustard.blurp.model.effects.Effect;

public abstract class EffectImpl extends Effect {

    protected void populateTween(BaseTween tween) {

        if(timesToRun != 1) {
            if(yoyoMode) {
                tween.repeatYoyo(timesToRun - 1, delayBetweenTimes);
            } else {
                tween.repeat(timesToRun - 1, delayBetweenTimes);
            }
        }
    }
}
