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
                world.setColour(Colours.Gold);
            } else if(world.mouse.isOver) {
                world.setColour(Colours.White);
            } else {
                world.setColour(Colours.GreenYellow);
            }

            if(world.mouse.isDragging) {
                world.setPosition(world.mouse.dragX, world.mouse.dragY);
            }

            if(world.mouse.wasDragReleased) {
                world.runEffect(effects.moveTo(400, 300).withStyle(EffectStyle.ElasticStop).withDuration(750));
            }

            if(world.mouse.wasClicked) {
                world.runEffect(effects.rotateBy(360).withDuration(750));
            }

            screen.update();
        }
    }
}
