package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.events.*;
import com.bigcustard.blurp.model.java.*;

public class EffectWithEventExample extends BlurpJavaProgram {

    @Override
    public void run() {

        EffectGroup destroyEffect = effects.combine(effects.scaleBy(4),
                                                  effects.alpha(0));

        while(true) {
            // Create a world...
            ImageSprite world = resources.createImageSprite("hello-world.png")
                    .position(utils.randomInRange(50, 750), utils.randomInRange(50, 550))
                    .scale(utils.randomInRange(0.1, 0.5));

            // And immediately destroy it. Harsh.
            world.runEffect(destroyEffect, SpriteEventHandler.AT_END_REMOVE_SPRITE);

            screen.update();
        }
    }
}
