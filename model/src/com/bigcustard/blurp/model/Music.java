package com.bigcustard.blurp.model;

public interface Music {

    public Music play();

    public Music play(double volume);

    public Music pause();

    public Music stop();

    public Music setPosition(int milliseconds);

    public Music setVolume(double volume);
}
