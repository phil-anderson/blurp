package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.java.*;

public class HelloCompoundEffectsWorld extends BlurpJavaProgram {

    @Override
    public void run() {

        effects.defaultDuration(0.5);

        EffectBase squish = effects.scaleTo(1, 0.7);
        EffectBase goRed = effects.colour(Colours.RED);
        EffectBase squishAndGoRed = effects.combine(squish, goRed);

        EffectBase spin = effects.rotateBy(720);
        EffectBase normalise = effects.scaleTo(1);
        EffectBase spinAndNormalise = effects.combine(spin, normalise);

        EffectBase animation = effects.sequence(squishAndGoRed, spinAndNormalise);
        animation = animation.timesToRun(4).yoyoMode(true).delayBetweenRuns(0.25);

        Image worldImage = blurp.loadImage("hello-world.png");
        ImageSprite world1 = blurp.createImageSprite(worldImage).position(250, 300);
        ImageSprite world2 = blurp.createImageSprite(worldImage).position(550, 300);

        world1.runEffect(animation);
        world2.runEffect(animation.delayBeforeStart(1));

        while(true) {
            blurp.blurpify();
        }
    }
}
