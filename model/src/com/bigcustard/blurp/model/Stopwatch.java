package com.bigcustard.blurp.model;

public class Stopwatch {

    private long startTime;
    private long stopTime;
    private boolean running;

    public void start() {

        if(running) return;

        startTime = System.nanoTime() - (stopTime - startTime);
        running = true;
    }

    public int stop() {

        if(running) {
            stopTime = System.nanoTime();
            running = false;
        }
        return elapsedTime();
    }

    public int elapsedTime() {

        long nanos = running ? System.nanoTime() - startTime
                             : stopTime - startTime;
        return (int) (nanos / 1000000);
    }

    public void reset() {

        startTime = 0;
        stopTime = 0;
        running = false;
    }
}
