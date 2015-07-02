package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.java.*;

public class EffectStyleExample extends BlurpJavaProgram {

    @Override
    public void run() {

        resources.createTextSprite("Press Space to Change Page").setPosition(400, 575);

        for(int x = 0; x < 3; x++) {

            int pageX = x * 800;
            resources.createTextSprite("Linear")
                .setPosition(250 + pageX, 500)
                .setHandle(Handle.Right)
                .setColour(Colours.Red);

            Sprite linearWorld = resources.createImageSprite("hello-world.png")
                                     .setPosition(350 + pageX, 500)
                                     .setScale(0.25)
                                     .setColour(Colours.Red);

            runEffect(linearWorld, EffectStyle.Linear, effects);

            for(int y = 0; y < 5; y++) {

                EffectStyle style = EffectStyle.values()[(y * 3 + x + 1)];

                int yPos = 400 - y * 90;
                resources.createTextSprite(style.name())
                    .setPosition(pageX + 250, yPos)
                    .setHandle(Handle.Right);

                Sprite effectWorld = resources.createImageSprite("hello-world.png")
                                         .setPosition(pageX + 350, yPos)
                                         .setScale(0.25);
                runEffect(effectWorld, style, effects);
            }
        }

        int currentPage = 0;
        while(true) {

            if(keyboard.Space.wasJustPressed()) {
                currentPage = (currentPage + 1) % 3;
                Effect moveCamera = effects.moveTo(currentPage * 800 + 400, 300).withDuration(500).withStyle(EffectStyle.BounceStop);
                camera.runEffect(moveCamera);
            }

            screen.update();
        }
    }

    // TODO: Refcator needed to remove need to pass effects instance here.
    private void runEffect(Sprite sprite, EffectStyle effectStyle, Effects effects) {

        Effect effect = effects.moveBy(300, 0)
                               .withStyle(effectStyle)
                               .withTimesToRun(1000000)
                               .withYoyoMode(true)
                               .withDelayBeforeStart(500)
                               .withDelayBetweenRuns(500);
        sprite.runEffect(effect);

    }
}
