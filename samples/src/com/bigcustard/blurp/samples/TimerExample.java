package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.java.*;

public class TimerExample extends BlurpJavaProgram {

    @Override
    public void run() {

        final ImageSprite world = resources.createImageSprite("hello-world.png");
        timer.every(1000, new Runnable() {
            @Override
            public void run() {

                world.runEffect(effects.rotateBy(6).duration(500).effectStyle(EffectStyle.BounceStop));
            }
        });

        while(screen.update()) {

        }
    }
}
