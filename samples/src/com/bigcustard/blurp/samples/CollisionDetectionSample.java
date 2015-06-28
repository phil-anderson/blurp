package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.java.*;

public class CollisionDetectionSample extends BlurpJavaProgram {

    @Override
    public void run() {

        system.debugMode(true);
        Image worldImage = resources.loadImage("hello-world.png");

        ImageSprite worldWithCircle1 = resources.createImageSprite(worldImage)
                                           .setScale(0.5)
                                           .setPosition(200, 500)
                                           .setTargetStyle(TargetStyle.Circle);

        ImageSprite worldWithCircle2 = resources.createImageSprite(worldImage)
                                           .setScale(0.5)
                                           .setPosition(600, 500)
                                           .setTargetStyle(TargetStyle.Circle);

        ImageSprite worldWithCircle3 = resources.createImageSprite(worldImage)
                                           .setScale(0.5)
                                           .setPosition(200, 300)
                                           .setTargetStyle(TargetStyle.Circle);

        ImageSprite worldWithRectangle1 = resources.createImageSprite(worldImage)
                                              .setScale(0.5)
                                              .setAngle(45)
                                              .setPosition(600, 300)
                                              .setTargetStyle(TargetStyle.Rectangle);

        ImageSprite worldWithRectangle2 = resources.createImageSprite(worldImage)
                                              .setScale(0.5)
                                              .setAngle(45)
                                              .setPosition(200, 100)
                                              .setTargetStyle(TargetStyle.Rectangle);

        ImageSprite worldWithRectangle3 = resources.createImageSprite(worldImage)
                                              .setScale(0.5)
                                              .setAngle(70)
                                              .setPosition(600, 100)
                                              .setTargetStyle(TargetStyle.Rectangle);

        while(true) {

            handleSpritePair(worldWithCircle1, worldWithCircle2);
            handleSpritePair(worldWithCircle3, worldWithRectangle1);
            handleSpritePair(worldWithRectangle2, worldWithRectangle3);
            screen.update();
        }
    }

    private void handleSpritePair(Sprite leftSprite, Sprite rightSprite) {

        if(!leftSprite.overlaps(rightSprite)) {
            leftSprite.rotateBy(1);
            rightSprite.rotateBy(-2);
            leftSprite.x += 2;
            rightSprite.x -= 2;
        }
    }
}