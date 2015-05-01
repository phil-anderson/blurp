package com.bigcustard.blurp.model.effects;

public abstract class Effect<T extends Effect> implements BaseEffect {

    public int timesToRun;
    public int delayBetweenTimes;
    public boolean yoyoMode;

    public Effect() {

        timesToRun = 1;
        delayBetweenTimes = 0;
        yoyoMode = false;
    }

    public T delayBetweenTimes(int delayBetweenTimes) {

        this.delayBetweenTimes = delayBetweenTimes;
        return (T) this;
    }

    public T timesToRun(int timesToRun) {

        this.timesToRun = timesToRun;
        return (T) this;
    }

    public T yoyoMode(boolean yoyo) {

        this.yoyoMode = yoyo;
        return (T) this;
    }
}
