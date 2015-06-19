package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.java.*;

public class EffectStyleExample extends BlurpJavaProgram {

    @Override
    public void run() {

        Image worldImage = resources.loadImage("hello-world.png");

        resources.createTextSprite("Press Space to Change Page").position(400, 575);

        for(int x = 0; x < 3; x++) {

            int pageX = x * 800;
            resources.createTextSprite("Linear")
                .position(250 + pageX, 500)
                .handle(Handle.Right)
                .colour(Colours.Red);

            Sprite linearWorld = resources.createImageSprite(worldImage)
                                     .position(350 + pageX, 500)
                                     .scale(0.25)
                                     .colour(Colours.Red);

            runEffect(linearWorld, EffectStyle.Linear, effects);

            for(int y = 0; y < 5; y++) {

                EffectStyle style = EffectStyle.values()[(y * 3 + x + 1)];

                int yPos = 400 - y * 90;
                resources.createTextSprite(style.name())
                    .position(pageX + 250, yPos)
                    .handle(Handle.Right);

                Sprite effectWorld = resources.createImageSprite(worldImage)
                                         .position(pageX + 350, yPos)
                                         .scale(0.25);
                runEffect(effectWorld, style, effects);
            }
        }

        int currentPage = 0;
        while(true) {

            if(keyboard.Space.wasJustPressed()) {
                currentPage = (currentPage + 1) % 3;
                Effect moveCamera = effects.moveTo(currentPage * 800 + 400, 300).duration(500).style(EffectStyle.BounceStop);
                camera.runEffect(moveCamera);
            }

            screen.update();
        }
    }

    // TODO: Refcator needed to remove need to pass effects instance here.
    private void runEffect(Sprite sprite, EffectStyle effectStyle, Effects effects) {

        Effect effect = effects.moveBy(300, 0)
                               .style(effectStyle)
                               .timesToRun(1000000)
                               .yoyoMode(true)
                               .delayBeforeStart(500)
                               .delayBetweenRuns(500);
        sprite.runEffect(effect);

    }
}
