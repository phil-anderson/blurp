package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;

public class HelloManyWorlds implements BlurpRunnable {

    private static final int NUM_WORLDS = 600;

    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils) {

        Image worldImage = blurp.loadImage("hello-world.png");
        ImageSprite[] worlds = new ImageSprite[NUM_WORLDS];

        for(int i = 0; i < NUM_WORLDS; i++) {

            double xPos = utils.random(150, 650);
            double yPos = utils.random(150, 450);

            worlds[i] = blurp.createImageSprite(worldImage)
                             .position(xPos, yPos)
                             .scale(utils.random(0.25, 1))
                             .rotateBy(utils.random(360));
        }

        while(true) {
            for(ImageSprite world : worlds) {
                world.rotateBy(world.x % 1 > 0.5 ? 3 : -3);
            }

            blurp.blurpify();
        }
    }
}
