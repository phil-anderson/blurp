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
                world.colour(Colours.Gold);
            } else if(world.mouse.isOver) {
                world.colour(Colours.White);
            } else {
                world.colour(Colours.GreenYellow);
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
