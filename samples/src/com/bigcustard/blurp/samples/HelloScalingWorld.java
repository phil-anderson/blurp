package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;

public class HelloScalingWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils) {

        ImageSprite world = blurp.imageSprite("world.png");

        while(true) {
            // Earthquake!
            world.scale(utils.random(0.95, 1.05));
            blurp.blurpify();
        }
    }
}
