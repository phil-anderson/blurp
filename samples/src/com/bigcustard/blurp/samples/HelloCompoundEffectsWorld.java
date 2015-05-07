package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;

public class HelloCompoundEffectsWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils, Effects effects) {

        effects.defaultDuration(0.5);

        Effect squish = effects.scale(1, 0.7);
        Effect goRed = effects.colour(Colours.RED);
        Effect squishAndGoRed = effects.combine(squish, goRed);

        Effect spin = effects.rotateBy(720);
        Effect normalise = effects.scale(1);
        Effect spinAndNormalise = effects.combine(spin, normalise);

        Effect animation = effects.sequence(squishAndGoRed, spinAndNormalise);
        animation = animation.timesToRun(4).delayBetweenRuns(0.25);

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
