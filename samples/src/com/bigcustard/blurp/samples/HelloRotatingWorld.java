package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;

public class HelloRotatingWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils, Keys keys) {

        ImageSprite world = blurp.imageSprite("world.png");

        while(true) {
            world.rotateBy(1);
            blurp.blurpify();
        }
    }
}
