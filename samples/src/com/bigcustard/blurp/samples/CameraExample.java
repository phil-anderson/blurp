package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.java.*;

public class CameraExample extends BlurpJavaProgram {

    @Override
    public void run() {

        Image worldImage = resources.loadImage("hello-world.png");

        Sprite[] sprites = new Sprite[8];
        for(int i = 0; i < 8; i++) {
            sprites[i] = resources.createImageSprite(worldImage)
                              .x(50 + i * 100)
                              .scale(utils.randomInRange(0.1, 0.5))
                              .rotation(utils.randomUpTo(360));
        }

        int spriteIndex = 0;
        while(true) {
            if(!camera.isRunningAnEffect()) {
                EffectBase alignWithSprite = buildCameraZoomInEffect(sprites[spriteIndex]);

                camera.runEffect(alignWithSprite.delayBeforeStart(500));
                spriteIndex = (spriteIndex + 1) % 8;
            }

            screen.update();
        }
    }

    private EffectGroup buildCameraZoomInEffect(Sprite sprite) {

        return effects.combine(
            effects.rotateTo(sprite.rotation),
            effects.moveTo(sprite.x, sprite.y),
            effects.zoom(1 / sprite.scaleX));
    }
}
