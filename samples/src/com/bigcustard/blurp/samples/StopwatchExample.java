package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;
import com.bigcustard.blurp.model.events.*;
import com.bigcustard.blurp.model.java.*;

public class StopwatchExample extends BlurpJavaProgram {

    @Override
    public void run() {

        effects.defaultDuration = 150;

        EffectGroup highlight = effects.combine(effects.scaleTo(1.5), effects.colour(Colours.WHITE));
        EffectGroup normal = effects.combine(effects.scaleTo(1), effects.colour(Colours.DARK_GREY));

        TextSprite startButton = resources.createTextSprite("Click to START")
            .position(200, 100)
            .colour(Colours.DARK_GREY)
            .whenMouseEnters(highlight)
            .whenMouseLeaves(normal)
            .whenClicked(new SpriteEventHandler() {
                @Override
                public void handle(Sprite sprite) {
                    timer.stopwatch.start();
                }
            });

        startButton.copy()
            .text("Click to STOP")
            .position(400, 100)
            .whenClicked(new SpriteEventHandler() {
            @Override
            public void handle(Sprite sprite) {
            timer.stopwatch.stop();
            }
            });

        startButton.copy()
            .text("Click to RESET")
            .position(600, 100)
            .whenClicked(new SpriteEventHandler() {
                @Override
                public void handle(Sprite sprite) {
                    timer.stopwatch.reset();
                }
            });

        TextSprite elapsedTime = resources.createTextSprite("")
                                     .fontSize(150)
                                     .scaleY(1.5)
                                     .colour(Colours.LIME_GREEN);
        while(screen.update()) {
            elapsedTime.text = timer.stopwatch.toString();
        }
    }
}
