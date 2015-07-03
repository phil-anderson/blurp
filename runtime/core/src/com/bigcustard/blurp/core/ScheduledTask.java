package com.bigcustard.blurp.core;

public class ScheduledTask {

    private final Runnable action;
    private long triggerTime;
    private final double repeatInterval;

    public ScheduledTask(Runnable action, long triggerTime, double repeatInterval) {

        this.action = action;
        this.triggerTime = triggerTime;
        this.repeatInterval = repeatInterval;
    }

//    150 530


    public boolean update() {

        long delta = BlurpState.frameStartTime - triggerTime;
        if(delta >= 0) {
            action.run();
            if(repeatInterval <= 0) {
                return true; // done
            }
            triggerTime = (long) (triggerTime + (delta / repeatInterval + 1) * repeatInterval);
        }
        return false;
    }

    public Runnable getAction() {

        return action;
    }

    public void delay(long milliseconds) {

        triggerTime += milliseconds;
    }
}
