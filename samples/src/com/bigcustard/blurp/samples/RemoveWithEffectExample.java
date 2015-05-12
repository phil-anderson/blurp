package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.EffectGroup;
import com.bigcustard.blurp.model.effects.Effects;

public class RemoveWithEffectExample implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Console console, Camera camera, Effects effects, Keyboard keyboard, Utils utils) {

        EffectGroup destroyEffect = effects.combine(effects.scaleBy(4),
                                                  effects.alpha(0));

        while(true) {
            // Create a world...
            ImageSprite world = blurp.createImageSprite("hello-world.png")
                    .position(utils.random(50, 750), utils.random(50, 550))
                    .scale(utils.random(0.1, 0.5));

            // And immediately destroy it. Harsh.
            world.removeWithEffect(destroyEffect);

            blurp.blurpify();
        }
    }
}
