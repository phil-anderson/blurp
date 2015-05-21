package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public class HelloRotatingWorldWithDebug implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Console console, Camera camera, Effects effects, Keyboard keyboard, Mouse mouse, Utils utils) {

        blurp.debugMode(true);
        ImageSprite world = blurp.createImageSprite("hello-world.png");

        while(true) {
            world.rotateBy(1);
            blurp.blurpify();
        }
    }
}
