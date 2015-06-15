package com.bigcustard.blurp.model;

public abstract class Timer {

    public final Stopwatch stopwatch;

    protected Timer() {

        stopwatch = new Stopwatch();
    }

    public abstract Timer after(int milliseconds, Runnable action);

    public abstract Timer every(int milliseconds, Runnable action);

    public abstract Timer remove(Runnable action);

    public abstract Timer removeAll();
}
