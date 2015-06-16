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

    public boolean update() {

        if(BlurpState.frameStartTime >= triggerTime) {
            action.run();
            if(repeatInterval <= 0) {
                return true; // done
            }
            triggerTime = (long) (triggerTime + repeatInterval);
        }
        return false;
    }

    public Runnable getAction() {

        return action;
    }
}
