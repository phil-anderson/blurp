package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.java.*;

public class SpriteMouseExample extends BlurpJavaProgram {

    @Override
    public void run() {

        ImageSprite world = resources.createImageSprite("hello-world.png");
        while(true) {

            if(world.mouseState.holding) {
                world.colour(Colours.GOLD);
            } else if(world.mouseState.over) {
                world.colour(Colours.WHITE);
            } else {
                world.colour(Colours.GREY);
            }

            if(world.mouseState.dragging) {
                world.position(world.mouseState.dragX, world.mouseState.dragY);
            }

            if(world.mouseState.dragReleased) {
                world.runEffect(effects.moveTo(400, 300).effectStyle(EffectStyle.ElasticStop).duration(750));
            }

            if(world.mouseState.clicked) {
                world.runEffect(effects.rotateBy(360).duration(750));
            }

            screen.update();
        }
    }
}
