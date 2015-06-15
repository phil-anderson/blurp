package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.java.*;

public class SpriteMouseExample extends BlurpJavaProgram {

    @Override
    public void run() {

        ImageSprite world = resources.createImageSprite("hello-world.png");
        while(true) {

            if(world.mouse.isHolding) {
                world.colour(Colours.GOLD);
            } else if(world.mouse.isOver) {
                world.colour(Colours.WHITE);
            } else {
                world.colour(Colours.GREY);
            }

            if(world.mouse.isDragging) {
                world.position(world.mouse.dragX, world.mouse.dragY);
            }

            if(world.mouse.wasDragReleased) {
                world.runEffect(effects.moveTo(400, 300).style(EffectStyle.ElasticStop).duration(750));
            }

            if(world.mouse.wasClicked) {
                world.runEffect(effects.rotateBy(360).duration(750));
            }

            screen.update();
        }
    }
}
