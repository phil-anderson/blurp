package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;

public class SpriteMultiplyExample extends BlurpJavaProgram {

    private static final int NUM_WORLDS = 100;

    @Override
    public void run() {

        ImageSprite[] worlds = blurp.createImageSprite("hello-world.png")
                                    .multiplyBy(NUM_WORLDS);

        for(ImageSprite world : worlds) {

            double xPos = utils.randomInRange(75, 725);
            double yPos = utils.randomInRange(75, 525);

            world.position(xPos, yPos)
                 .scale(utils.randomInRange(0.25, 0.5))
                 .rotateBy(utils.randomUpTo(360));
        }

        while(blurp.updateScreen()) {

            for(ImageSprite world : worlds) {
                world.rotateBy(world.x % 1 > 0.5 ? 3 : -3);
            }
        }
    }
}
