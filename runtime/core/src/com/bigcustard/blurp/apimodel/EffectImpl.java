package com.bigcustard.blurp.apimodel;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public abstract class EffectImpl<T extends Effect> extends Effect<T> {

    protected void populateTween(BaseTween tween) {

        if(delayBeforeStart != 0) {
            tween.delay((float) delayBeforeStart);
        }
        if(timesToRun != 1) {
            if(yoyoMode) {
                tween.repeatYoyo(timesToRun - 1, (float) delayBetweenRuns);
            } else {
                tween.repeat(timesToRun - 1, (float) delayBetweenRuns);
            }
        }
    }

    public abstract BaseTween getTween(Sprite sprite);
}
