package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;

public class HelloAlphaChangingWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils) {

        Image worldImage = blurp.image("hello-world.png");

        blurp.imageSprite(worldImage);
        ImageSprite upsideDownWorld = blurp.imageSprite(worldImage).rotateBy(180); // Upside-down world exactly covering the normal one

        while(true) {
            // Fade the upside-down one in and out, thus covering and revealing the normal one.
            upsideDownWorld.alpha = utils.wave(0, 1, 2000);
            blurp.blurpify();
        }
    }
}
