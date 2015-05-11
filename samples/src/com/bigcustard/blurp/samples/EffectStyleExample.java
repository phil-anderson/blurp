package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;

public class EffectStyleExample implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Console console, Camera camera, Effects effects, Keyboard keyboard, Utils utils) {

        Image worldImage = blurp.loadImage("hello-world.png");

        effects.defaultDuration(1);

        blurp.createTextSprite("Press Space to Change Page").position(400, 575);

        for(int x = 0; x < 3; x++) {

            int pageX = x * 800;
            blurp.createTextSprite("Linear")
                .position(250 + pageX, 500)
                .handle(Handle.Right)
                .colour(Colours.RED);

            Sprite linearWorld = blurp.createImageSprite(worldImage)
                                     .position(350 + pageX, 500)
                                     .scale(0.25)
                                     .colour(Colours.RED);

            runEffect(linearWorld, EffectStyle.Linear, effects);

            for(int y = 0; y < 5; y++) {

                EffectStyle style = EffectStyle.values()[(y * 3 + x + 1)];

                int yPos = 400 - y * 90;
                blurp.createTextSprite(style.name())
                    .position(pageX + 250, yPos)
                    .handle(Handle.Right);

                Sprite effectWorld = blurp.createImageSprite(worldImage)
                                         .position(pageX + 350, yPos)
                                         .scale(0.25);
                runEffect(effectWorld, style, effects);
            }
        }

        int currentPage = 0;
        while(true) {

            if(keyboard.isKeyJustPressed(Key.Key_Space)) {
                currentPage = (currentPage + 1) % 3;
                Effect moveCamera = effects.moveTo(currentPage * 800 + 400, 300).duration(0.5);
                camera.runEffect(moveCamera);
            }

            blurp.blurpify();
        }
    }

    // TODO: Refcator needed to remove need to pass effects instance here.
    private void runEffect(Sprite sprite, EffectStyle effectStyle, Effects effects) {

        Effect effect = effects.moveBy(300, 0)
                               .effectStyle(effectStyle)
                               .timesToRun(1000000)
                               .yoyoMode(true)
                               .delayBeforeStart(0.5)
                               .delayBetweenRuns(0.5);
        sprite.runEffect(effect);

    }
}
