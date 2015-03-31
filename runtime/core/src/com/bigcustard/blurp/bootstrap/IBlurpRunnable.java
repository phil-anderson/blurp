package com.bigcustard.blurp.bootstrap;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public interface IBlurpRunnable {

    // The idea is that the ScriptEngine-based runner will set the params up as globals
    public void run(Blurp blurp, Canvas canvas, IKeyboard keyboard, Utils utils);
}
