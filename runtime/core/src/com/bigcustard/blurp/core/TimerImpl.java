package com.bigcustard.blurp.core;

import java.util.*;
import com.bigcustard.blurp.model.Timer;

public class TimerImpl extends Timer {

    private List<ScheduledTask> scheduledTasks;

    public TimerImpl() {

        scheduledTasks = new ArrayList<ScheduledTask>();
    }

    public void dispatchEvents() {

        for(Iterator<ScheduledTask> iter = scheduledTasks.iterator(); iter.hasNext(); ) {
            if(iter.next().update()) {
                iter.remove();
            }
        }
    }

    @Override
    public Timer after(int milliseconds, Runnable action) {

        scheduledTasks.add(new ScheduledTask(action, BlurpState.frameStartTime + milliseconds, -1));
        return this;
    }

    @Override
    public Timer every(int milliseconds, Runnable action) {

        scheduledTasks.add(new ScheduledTask(action, BlurpState.frameStartTime + milliseconds, milliseconds));
        return this;
    }

    @Override
    public Timer remove(Runnable action) {

        while(scheduledTasks.remove(action)) { }
        return this;
    }

    @Override
    public Timer removeAll() {

        scheduledTasks.clear();
        return this;
    }

    public void reset() {

        removeAll();
    }
}
