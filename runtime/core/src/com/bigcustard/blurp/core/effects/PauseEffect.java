package com.bigcustard.blurp.core.effects;

import com.bigcustard.blurp.model.effects.BaseEffect;

public class PauseEffect implements BaseEffect {

    private final int duration;

    public PauseEffect(int duration) {

        this.duration = duration;
    }

    public int getDuration() {

        return duration;
    }
}
