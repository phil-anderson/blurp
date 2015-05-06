package com.bigcustard.blurp.model.effects;

public abstract class Effect<T extends Effect> {

    protected int timesToRun = 1;
    protected double delayBeforeStart = 0;
    protected double delayBetweenRuns = 0;

    public T timesToRun(int timesToRun) {

        if(timesToRun <= 0) throw new IllegalArgumentException("Effects must run at least once");

        T copy = copy((T) this);
        copy.timesToRun = timesToRun;
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
        other.delayBeforeStart = this.delayBeforeStart;
        other.delayBetweenRuns = this.delayBetweenRuns;
    }
}
