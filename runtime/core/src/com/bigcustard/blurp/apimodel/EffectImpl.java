package com.bigcustard.blurp.apimodel;

import aurelienribon.tweenengine.*;
import com.bigcustard.blurp.model.effects.*;

// TODO: I hate the casts in this class, but Java's type system is a mare.
public abstract class EffectImpl<T extends EffectBase> implements EffectBase<T> {

    protected int timesToRun = 1;
    protected boolean yoyoMode = false;
    protected double delayBeforeStart = 0;
    protected double delayBetweenRuns = 0;

    public T timesToRun(int timesToRun) {

        if(timesToRun <= 0) throw new IllegalArgumentException("Effects must run at least once");

        EffectImpl<T> copy = (EffectImpl<T>) copy((T) this);
        copy.timesToRun = timesToRun;
        return (T) copy;
    }

    @Override
    public T yoyoMode(boolean yoyoMode) {

        EffectImpl<T> copy = (EffectImpl<T>) copy((T) this);
        copy.yoyoMode = yoyoMode;
        return (T) copy;
    }

    public T delayBeforeStart(double delayBeforeStart) {

        EffectImpl<T> copy = (EffectImpl<T>) copy((T) this);
        copy.delayBeforeStart = delayBeforeStart;
        return (T) copy;
    }

    public T delayBetweenRuns(double delayBetweenRuns) {

        EffectImpl<T> copy = (EffectImpl<T>) copy((T) this);
        copy.delayBetweenRuns = delayBetweenRuns;
        return (T) copy;
    }

    protected abstract T copy(T effect);

    protected void copyBasePropertiesTo(EffectImpl other) {

        other.timesToRun = this.timesToRun;
        other.yoyoMode = this.yoyoMode;
        other.delayBeforeStart = this.delayBeforeStart;
        other.delayBetweenRuns = this.delayBetweenRuns;
    }

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

    public abstract BaseTween getTween(Object target);
}
