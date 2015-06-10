package com.bigcustard.blurp.core.events;

import com.bigcustard.blurp.core.*;

public class TimerEventDispatcher {

    public void dispatchEvents() {

        BlurpStore.timer.dispatchEvents();
    }
}
