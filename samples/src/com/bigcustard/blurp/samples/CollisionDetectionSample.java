package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;

public class CollisionDetectionSample implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Console console, Camera camera, Effects effects, Keyboard keyboard, Utils utils) {

        blurp.setDebugMode(true);
        Image worldImage = blurp.loadImage("hello-world.png");

        ImageSprite worldWithCircle1 = blurp.createImageSprite(worldImage)
                                           .scale(0.5)
                                           .position(200, 500)
                                           .collisionShape(CollisionShape.CenterCircle);

        ImageSprite worldWithCircle2 = blurp.createImageSprite(worldImage)
                                           .scale(0.5)
                                           .position(600, 500)
                                           .collisionShape(CollisionShape.CenterCircle);

        ImageSprite worldWithCircle3 = blurp.createImageSprite(worldImage)
                                           .scale(0.5)
                                           .position(200, 300)
                                           .collisionShape(CollisionShape.CenterCircle);

        ImageSprite worldWithRectangle1 = blurp.createImageSprite(worldImage)
                                              .scale(0.5)
                                              .rotation(45)
                                              .position(600, 300)
                                              .collisionShape(CollisionShape.BoundaryRectangle);

        ImageSprite worldWithRectangle2 = blurp.createImageSprite(worldImage)
                                              .scale(0.5)
                                              .rotation(45)
                                              .position(200, 100)
                                              .collisionShape(CollisionShape.BoundaryRectangle);

        ImageSprite worldWithRectangle3 = blurp.createImageSprite(worldImage)
                                              .scale(0.5)
                                              .rotation(70)
                                              .position(600, 100)
                                              .collisionShape(CollisionShape.BoundaryRectangle);

        while(true) {

            handleSpritePair(worldWithCircle1, worldWithCircle2);
            handleSpritePair(worldWithCircle3, worldWithRectangle1);
            handleSpritePair(worldWithRectangle2, worldWithRectangle3);
            blurp.blurpify();
        }
    }

    private void handleSpritePair(Sprite leftSprite, Sprite rightSprite) {

        if(!leftSprite.collidedWith(rightSprite)) {
            leftSprite.rotateBy(1);
            rightSprite.rotateBy(-2);
            leftSprite.x += 2;
            rightSprite.x -= 2;
        }
    }
}