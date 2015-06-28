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

        EffectGroup highlight = effects.combine(effects.scaleTo(1.5), effects.colour(Colours.White));
        EffectGroup normal = effects.combine(effects.scaleTo(1), effects.colour(Colours.DarkGrey));

        TextSprite startButton = resources.createTextSprite("Click to START")
            .setPosition(200, 100)
            .setColour(Colours.DarkGrey)
            .onMouseEnter(highlight)
            .onMouseExit(normal)
            .onClick(new SpriteEventHandler() {
                @Override
                public void handle(Sprite sprite) {

                    timer.stopwatch.start();
                }
            });

        startButton.copy()
            .setText("Click to STOP")
            .setPosition(400, 100)
            .onClick(new SpriteEventHandler() {
                @Override
                public void handle(Sprite sprite) {

                    timer.stopwatch.stop();
                }
            });

        startButton.copy()
            .setText("Click to RESET")
            .setPosition(600, 100)
            .onClick(new SpriteEventHandler() {
                @Override
                public void handle(Sprite sprite) {

                    timer.stopwatch.reset();
                }
            });

        TextSprite elapsedTime = resources.createTextSprite("")
                                     .setFontSize(150)
                                     .setScaleY(1.5)
                                     .setColour(Colours.LimeGreen);
        while(screen.update()) {
            elapsedTime.text = timer.stopwatch.toString();
        }
    }
}
