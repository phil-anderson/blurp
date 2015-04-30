package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;

public class HelloUnevenlyScalingWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils) {

        ImageSprite world = blurp.createImageSprite("hello-world.png");

        while(true) {
            double waveValue = utils.wave(0, 0.5, 1000);
            world.scaleX(0.75 + waveValue);
            world.scaleY(1.25 - waveValue);
            blurp.blurpify();
        }
    }
}
