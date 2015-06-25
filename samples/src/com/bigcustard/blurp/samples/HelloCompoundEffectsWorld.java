package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.java.*;

public class HelloCompoundEffectsWorld extends BlurpJavaProgram {

    @Override
    public void run() {

        effects.setDefaultDuration(500);

        EffectBase squish = effects.scaleTo(1, 0.7);
        EffectBase goRed = effects.colour(Colours.Red);
        EffectBase squishAndGoRed = effects.combine(squish, goRed);

        EffectBase spin = effects.rotateBy(720);
        EffectBase normalise = effects.scaleTo(1);
        EffectBase spinAndNormalise = effects.combine(spin, normalise);

        EffectBase animation = effects.sequence(squishAndGoRed, spinAndNormalise);
        animation = animation.timesToRun(4).yoyoMode(true).delayBetweenRuns(250);

        Image worldImage = resources.loadImage("hello-world.png");
        ImageSprite world1 = resources.createImageSprite(worldImage).setPosition(250, 300);
        ImageSprite world2 = resources.createImageSprite(worldImage).setPosition(550, 300);

        world1.runEffect(animation);
        world2.runEffect(animation.delayBeforeStart(1000));

        while(true) {
            screen.update();
        }
    }
}
