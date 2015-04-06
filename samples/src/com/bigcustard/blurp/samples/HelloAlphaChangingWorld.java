package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class HelloAlphaChangingWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils) {

        Image worldImage = blurp.image("world.png");

        ImageSprite upsideDownWorld = blurp.imageSprite(worldImage);
        ImageSprite world = blurp.imageSprite(worldImage).rotateBy(180); // Upside-down world exactly covering the normal one

        int index = 0;

        while(true) {
            // Fade the upside-down one in and out, hiding and revealing the normal one.
            world.alpha = utils.sin(index) / 2 + 0.5;
            index += 2;

            blurp.blurpify();
        }
    }
}
