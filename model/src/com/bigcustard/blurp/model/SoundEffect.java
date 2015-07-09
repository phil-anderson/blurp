package com.bigcustard.blurp.model;

public abstract class SoundEffect {

    public SoundEffect play() {

        return play(1, 1);
    }

    public SoundEffect play(double volume) {

        return play(volume, 1);
    }

    public abstract SoundEffect play(double volume, double speed);
}
