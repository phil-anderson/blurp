package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;

public class EffectStyleExample implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Camera camera, Effects effects, Keyboard keyboard, Utils utils) {

        Image worldImage = blurp.loadImage("hello-world.png");
        Sprite[] worldSprites = new Sprite[16];

        effects.defaultDuration(1);

        for(int i = 0; i < 16; i++) {
            int yPosition = 560 - i * 35;
            blurp.createTextSprite(EffectStyle.values()[i].name()).position(300, yPosition).handle(Handle.Right);
            worldSprites[i] = blurp.createImageSprite(worldImage).position(350, yPosition).scale(0.11);
            Effect effect = effects.moveBy(300, 0)
                                   .effectStyle(EffectStyle.values()[i])
                                   .timesToRun(100)
                                   .delayBeforeStart(0.5)
                                   .delayBetweenRuns(0.5);

            worldSprites[i].runEffect(effect);
        }

        while(true) {
            blurp.blurpify();
        }
    }
}
