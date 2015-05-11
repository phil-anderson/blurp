package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public class HelloMovingWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Console console, Camera camera, Effects effects, Keyboard keyboard, Utils utils) {

        ImageSprite world = blurp.createImageSprite("hello-world.png");

        double targetX = 400;
        double targetY = 300;

        while(true) {

            world.moveTowards(targetX, targetY, 7.5);
            blurp.blurpify();

            if(world.x == targetX && world.y == targetY) {
                targetX = utils.random(150, 650);
                targetY = utils.random(150, 450);
            }
        }
    }
}
