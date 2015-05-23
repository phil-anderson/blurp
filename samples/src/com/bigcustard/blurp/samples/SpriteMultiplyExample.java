package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;

public class SpriteMultiplyExample extends BlurpJavaProgram {

    private static final int NUM_WORLDS = 20;

    @Override
    public void run() {

        ImageSprite[] worlds = blurp.createImageSprite("hello-world.png")
                                    .multiplyBy(NUM_WORLDS);

        for(ImageSprite world : worlds) {

            double xPos = utils.randomInRange(150, 650);
            double yPos = utils.randomInRange(150, 450);

            world.position(xPos, yPos)
                 .scale(utils.randomInRange(0.25, 1))
                 .rotateBy(utils.randomUpTo(360));
        }

        while(true) {

            for(ImageSprite world : worlds) {
                world.rotateBy(world.x % 1 > 0.5 ? 3 : -3);
            }
            blurp.blurpify();
        }
    }
}
