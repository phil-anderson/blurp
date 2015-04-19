package com.bigcustard.blurp.samples;

import com.badlogic.gdx.*;
import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.Screen;

public class HelloManyWorlds implements BlurpRunnable {

    private static final int NUM_WORLDS = 600;

    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils, Keys keys) {

        Image worldImage = blurp.image("world.png");
        ImageSprite[] worlds = new ImageSprite[NUM_WORLDS];
        for(int i = 0; i < NUM_WORLDS; i++) {
            double xPos = utils.random(150, 650);
            double yPos = utils.random(150, 450);
            worlds[i] = blurp.imageSprite(worldImage)
                            .position(xPos, yPos)
                            .scale(utils.random(0.25, 1))
                            .rotateBy(utils.random(360));
        }

        while(true) {
            for(ImageSprite world : worlds) {
                world.rotateBy(world.x % 1 > 0.5 ? 3 : -3);
            }
            Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond() + " FPS");
            blurp.blurpify();
        }
    }
}