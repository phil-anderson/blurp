package com.bigcustard.blurp.core.events;

import com.bigcustard.blurp.core.*;

public class TimerEventDispatcher {

    public void dispatchEvents() {

        ((TimerImpl) BlurpStore.timer).dispatchEvents();
    }
}
