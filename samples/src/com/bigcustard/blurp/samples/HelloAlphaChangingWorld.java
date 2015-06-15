package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.java.*;

public class HelloAlphaChangingWorld extends BlurpJavaProgram {

    @Override
    public void run() {

        Image worldImage = resources.loadImage("hello-world.png");

        resources.createImageSprite(worldImage);
        ImageSprite upsideDownWorld = resources.createImageSprite(worldImage).rotateBy(180); // Upside-down world exactly covering the normal one

        while(true) {
            // Fade the upside-down one in and out, thus covering and revealing the normal one.
            upsideDownWorld.transparency = utils.waveValue(0, 1, 2000);
            screen.update();
        }
    }
}
