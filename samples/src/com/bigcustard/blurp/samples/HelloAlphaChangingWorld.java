package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public class HelloAlphaChangingWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils, Effects effects) {

        Image worldImage = blurp.loadImage("hello-world.png");

        blurp.createImageSprite(worldImage);
        ImageSprite upsideDownWorld = blurp.createImageSprite(worldImage).rotateBy(180); // Upside-down world exactly covering the normal one

        while(true) {
            // Fade the upside-down one in and out, thus covering and revealing the normal one.
            upsideDownWorld.alpha = utils.wave(0, 1, 2000);
            blurp.blurpify();
        }
    }
}
