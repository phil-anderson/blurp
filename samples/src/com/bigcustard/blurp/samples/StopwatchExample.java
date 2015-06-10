package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.events.*;
import com.bigcustard.blurp.model.java.*;

public class StopwatchExample extends BlurpJavaProgram {

    @Override
    public void run() {

        blurp.createTextSprite("Click to START")
            .position(200, 100)
            .whenClicked(new SpriteEventHandler() {
                @Override
                public void handle(Sprite sprite) {
                    timer.stopwatch.start();
                }
            });
        blurp.createTextSprite("Click to STOP")
            .position(400, 100)
            .whenClicked(new SpriteEventHandler() {
                @Override
                public void handle(Sprite sprite) {
                    timer.stopwatch.stop();
                }
            });
        blurp.createTextSprite("Click to RESET")
            .position(600, 100)
            .whenClicked(new SpriteEventHandler() {
                @Override
                public void handle(Sprite sprite) {
                    timer.stopwatch.reset();
                }
            });

        TextSprite elapsedTime = blurp.createTextSprite("").fontSize(100).colour(Colours.LIME_GREEN);
        while(blurp.updateScreen()) {
            elapsedTime.text = "" + timer.stopwatch.elapsedTime();
        }
    }
}
