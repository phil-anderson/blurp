package com.bigcustard.blurp.model;

import java.lang.System;

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

    @Override
    public String toString() {

        int time = elapsedTime();
        int hours = time / 3600000;
        int minutes = (time % 3600000) / 60000;
        int seconds = (time % 60000) / 1000;
        int millis = time % 1000;

        return String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, millis);
    }

    public void reset() {

        startTime = 0;
        stopTime = 0;
        running = false;
    }
}
