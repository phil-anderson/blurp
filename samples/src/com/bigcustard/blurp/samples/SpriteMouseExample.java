package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;

public class SpriteMouseExample implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Console console, Camera camera, Effects effects, Keyboard keyboard, Mouse mouse, Utils utils) {

        ImageSprite world = blurp.createImageSprite("hello-world.png");
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
                world.runEffect(effects.moveTo(400, 300).effectStyle(EffectStyle.ElasticStop).duration(0.75));
            }

            if(world.mouseState.clicked) {
                world.runEffect(effects.rotateBy(360).duration(0.5));
            }

            blurp.blurpify();
        }
    }
}
