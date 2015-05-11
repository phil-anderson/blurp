package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public class HelloAlphaChangingWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Console console, Camera camera, Effects effects, Keyboard keyboard, Utils utils) {

        Image worldImage = blurp.loadImage("hello-world.png");

        blurp.createImageSprite(worldImage);
        ImageSprite upsideDownWorld = blurp.createImageSprite(worldImage).rotateBy(180); // Upside-down world exactly covering the normal one

        while(true) {
            // Fade the upside-down one in and out, thus covering and revealing the normal one.
            upsideDownWorld.alpha = utils.wave(0, 1, 2);
            blurp.blurpify();
        }
    }
}
