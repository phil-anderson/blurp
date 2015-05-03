package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public class HelloScalingWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils, Effects effects) {

        ImageSprite world = blurp.createImageSprite("hello-world.png");

        while(true) {
            // Earthquake!
            world.scale(utils.random(0.95, 1.05));
            blurp.blurpify();
        }
    }
}
