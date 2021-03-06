package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.java.*;

public class CameraExample extends BlurpJavaProgram {

    @Override
    public void run() {

        Sprite[] sprites = new Sprite[8];
        for(int i = 0; i < 8; i++) {
            sprites[i] = resources.createImageSprite("hello-world.png")
                              .setX(50 + i * 100)
                              .setScale(utils.randomInRange(0.1, 0.5))
                              .setAngle(utils.randomUpTo(360));
        }

        int spriteIndex = 0;
        while(true) {
            if(!camera.isRunningAnEffect()) {
                EffectBase alignWithSprite = buildCameraZoomInEffect(sprites[spriteIndex]);

                camera.runEffect(alignWithSprite.withDelayBeforeStart(500));
                spriteIndex = (spriteIndex + 1) % 8;
            }

            screen.update();
        }
    }

    private EffectGroup buildCameraZoomInEffect(Sprite sprite) {

        return effects.combine(
            effects.rotateTo(sprite.angle),
            effects.moveTo(sprite.x, sprite.y),
            effects.zoomTo(1 / sprite.scaleX));
    }
}
