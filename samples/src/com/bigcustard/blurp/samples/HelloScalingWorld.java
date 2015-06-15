package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.java.*;

public class HelloScalingWorld extends BlurpJavaProgram {

    @Override
    public void run() {

        ImageSprite world = resources.createImageSprite("hello-world.png");

        while(true) {
            // Earthquake!
            world.scale(utils.randomInRange(0.95, 1.05));
            screen.update();
        }
    }
}
