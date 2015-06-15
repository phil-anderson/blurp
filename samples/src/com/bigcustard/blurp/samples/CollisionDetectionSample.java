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
                                           .scale(0.5)
                                           .position(200, 500)
                                           .collisionShape(CollisionShape.CenterCircle);

        ImageSprite worldWithCircle2 = resources.createImageSprite(worldImage)
                                           .scale(0.5)
                                           .position(600, 500)
                                           .collisionShape(CollisionShape.CenterCircle);

        ImageSprite worldWithCircle3 = resources.createImageSprite(worldImage)
                                           .scale(0.5)
                                           .position(200, 300)
                                           .collisionShape(CollisionShape.CenterCircle);

        ImageSprite worldWithRectangle1 = resources.createImageSprite(worldImage)
                                              .scale(0.5)
                                              .angle(45)
                                              .position(600, 300)
                                              .collisionShape(CollisionShape.BoundaryRectangle);

        ImageSprite worldWithRectangle2 = resources.createImageSprite(worldImage)
                                              .scale(0.5)
                                              .angle(45)
                                              .position(200, 100)
                                              .collisionShape(CollisionShape.BoundaryRectangle);

        ImageSprite worldWithRectangle3 = resources.createImageSprite(worldImage)
                                              .scale(0.5)
                                              .angle(70)
                                              .position(600, 100)
                                              .collisionShape(CollisionShape.BoundaryRectangle);

        while(true) {

            handleSpritePair(worldWithCircle1, worldWithCircle2);
            handleSpritePair(worldWithCircle3, worldWithRectangle1);
            handleSpritePair(worldWithRectangle2, worldWithRectangle3);
            screen.update();
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