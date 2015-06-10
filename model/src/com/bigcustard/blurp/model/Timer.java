package com.bigcustard.blurp.model;

public abstract class Timer {

    public final Stopwatch stopwatch;

    protected Timer() {

        stopwatch = new Stopwatch();
    }

    public abstract Timer after(long milliseconds, Runnable action);

    public abstract Timer every(long milliseconds, Runnable action);

    public abstract Timer remove(Runnable action);

    public abstract Timer clear();
}
