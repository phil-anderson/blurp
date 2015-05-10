package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public class HelloUnevenlyScalingWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Camera camera, Effects effects, Keyboard keyboard, Utils utils) {

        ImageSprite world = blurp.createImageSprite("hello-world.png");

        while(true) {
            double waveValue = utils.wave(0, 0.5, 1);
            world.scaleX(0.75 + waveValue);
            world.scaleY(1.25 - waveValue);
            blurp.blurpify();
        }
    }
}
