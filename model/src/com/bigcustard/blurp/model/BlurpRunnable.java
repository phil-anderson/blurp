package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.effects.*;

public interface BlurpRunnable {

    public void run(Blurp blurp, Screen screen, Console console, Camera camera, Effects effects, Keyboard keyboard, Utils utils);
}
