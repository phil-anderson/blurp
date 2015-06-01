package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.java.*;

public class HelloMovingWorld extends BlurpJavaProgram {

    @Override
    public void run() {

        ImageSprite world = blurp.createImageSprite("hello-world.png");

        double targetX = 400;
        double targetY = 300;

        while(true) {

            world.moveTowards(targetX, targetY, 7.5);
            blurp.blurpify();

            if(world.x == targetX && world.y == targetY) {
                targetX = utils.randomInRange(150, 650);
                targetY = utils.randomInRange(150, 450);
            }
        }
    }
}
