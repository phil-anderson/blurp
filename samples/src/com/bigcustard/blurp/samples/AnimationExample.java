package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.java.*;

public class AnimationExample extends BlurpJavaProgram {

    @Override
    public void run() {

        final AnimationSprite man1 = resources.createAnimationSprite("stickman0.png", "stickman1.png", "stickman2.png",
                                                                     "stickman3.png", "stickman4.png", "stickman5.png",
                                                                     "stickman6.png", "stickman7.png", "stickman8.png");
        man1.play(50);

        while(screen.update()) {

            if(keyboard.Space.wasJustPressed()) {
                if(man1.isPlaying()) {
                    man1.stop();
                } else {
                    man1.play(50);
                }
            }
            if(keyboard.R.wasJustPressed()) {
                man1.reverse = !man1.reverse;
            }
        }
    }
}
