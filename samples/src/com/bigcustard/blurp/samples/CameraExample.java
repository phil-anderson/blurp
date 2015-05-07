package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public class CameraExample implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Camera camera, Keyboard keyboard, Utils utils, Effects effects) {

        Image worldImage = blurp.loadImage("hello-world.png");

        Sprite[] sprites = new Sprite[8];
        for(int i = 0; i < 8; i++) {
            sprites[i] = blurp.createImageSprite(worldImage)
                              .x(50 + i * 100)
                              .scale(utils.random(0.2, 0.5))
                              .rotation(utils.random(360));
        }

        int spriteIndex = 0;
        while(true) {
            if(!camera.isRunningEffect()) {
                Effect alignWithSprite = effects.combine(
                    effects.rotateTo(sprites[spriteIndex].rotation),
                    effects.moveTo(sprites[spriteIndex].x, sprites[spriteIndex].y),
                    effects.zoom(1 / sprites[spriteIndex].scaleX));

                camera.runEffect(alignWithSprite.delayBeforeStart(0.5));
                spriteIndex = (spriteIndex + 1) % 8;
            }
            blurp.blurpify();
        }
    }
}
