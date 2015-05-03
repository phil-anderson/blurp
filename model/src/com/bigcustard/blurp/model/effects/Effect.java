package com.bigcustard.blurp.model.effects;

public abstract class Effect<T extends Effect> implements BaseEffect {

    protected int timesToRun = 1;
    protected boolean yoyoMode = false;
    protected double delayBeforeStart = 0;
    protected double delayBetweenRuns = 0;

    public T timesToRun(int timesToRun) {

        T copy = copy((T) this);
        copy.timesToRun = timesToRun;
        return copy;
    }

    public T yoyoMode(boolean yoyo) {

        T copy = copy((T) this);
        copy.yoyoMode = yoyo;
        return copy;
    }

    public T delayBeforeStart(double delayBeforeStart) {

        T copy = copy((T) this);
        copy.delayBeforeStart = delayBeforeStart;
        return copy;
    }

    public T delayBetweenRuns(double delayBetweenRuns) {

        T copy = copy((T) this);
        copy.delayBetweenRuns = delayBetweenRuns;
        return copy;
    }

    protected abstract T copy(T effect);

    protected void copyBasePropertiesTo(Effect other) {

        other.timesToRun = this.timesToRun;
        other.yoyoMode = this.yoyoMode;
        other.delayBeforeStart = this.delayBeforeStart;
        other.delayBetweenRuns = this.delayBetweenRuns;
    }
}
