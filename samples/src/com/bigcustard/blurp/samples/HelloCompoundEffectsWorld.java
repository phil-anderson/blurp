package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;

public class HelloCompoundEffectsWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils, Effects effects) {

        effects.defaultDuration(5);

        Effect widen = effects.scale(2, 1);
        Effect goRed = effects.colour(Colours.RED);
        Effect widenAndGoRed = effects.combine(widen, goRed);

        Effect spin = effects.rotateBy(720);
        Effect fade = effects.alpha(0);
        Effect spinAndFade = effects.combine(spin, fade);

        Effect animation = effects.sequence(widenAndGoRed, spinAndFade);
        animation.timesToRun(100).yoyoMode(true).delayBetweenRuns(250);

        Image worldImage = blurp.loadImage("hello-world.png");
        ImageSprite world1 = blurp.createImageSprite(worldImage).position(250, 300);
        ImageSprite world2 = blurp.createImageSprite(worldImage).position(550, 300);

//        world1.effect(animation);
        world2.effect(animation.delayBeforeStart(1));

        while(true) {
            blurp.blurpify();
        }
    }
}
