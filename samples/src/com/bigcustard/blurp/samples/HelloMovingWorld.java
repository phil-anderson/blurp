package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;

public class HelloMovingWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils) {

        ImageSprite world = blurp.imageSprite("world.png");

        double targetX = 400;
        double targetY = 300;

        while(true) {

            world.moveTowards(targetX, targetY, 500);
            blurp.blurpify();

            if(world.x == targetX && world.y == targetY) {
                targetX = utils.random(150, 650);
                targetY = utils.random(150, 450);
            }
        }
    }
}
