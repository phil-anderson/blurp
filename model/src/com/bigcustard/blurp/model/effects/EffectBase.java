package com.bigcustard.blurp.model.effects;

public interface EffectBase<T extends EffectBase> {

    public T withTimesToRun(int timesToRun);

    public T withYoyoMode(boolean yoyoMode);

    public T withDelayBeforeStart(int delayBeforeStart);

    public T withDelayBetweenRuns(int delayBetweenRuns);
}
