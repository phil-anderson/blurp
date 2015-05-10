package com.bigcustard.blurp.model.effects;

public interface EffectBase<T extends EffectBase> {

    public T timesToRun(int timesToRun);

    public T yoyoMode(boolean yoyoMode);

    public T delayBeforeStart(double delayBeforeStart);

    public T delayBetweenRuns(double delayBetweenRuns);
}
