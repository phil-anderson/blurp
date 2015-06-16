package com.bigcustard.blurp.core;

import java.util.*;
import com.bigcustard.blurp.model.Timer;

public class TimerImpl extends Timer {

    private List<ScheduledTask> scheduledTasks;

    public TimerImpl() {

        scheduledTasks = new ArrayList<ScheduledTask>();
    }

    public void dispatchEvents() {

        // Use a copy in case action triggered by task.update() wants to remove tasks
        ArrayList<ScheduledTask> scheduledTasksCopy = new ArrayList<ScheduledTask>(scheduledTasks);
        for(Iterator<ScheduledTask> iter = scheduledTasksCopy.iterator(); iter.hasNext(); ) {
            ScheduledTask task = iter.next();
            if(task.update()) {
                scheduledTasks.remove(task);
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

        for(Iterator<ScheduledTask> iter = scheduledTasks.iterator(); iter.hasNext(); ) {
            ScheduledTask task = iter.next();
            if(task.getAction() == action) {
                iter.remove();
            }
        }
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
