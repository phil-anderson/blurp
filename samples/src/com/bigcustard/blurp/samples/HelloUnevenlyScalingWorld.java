package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.java.*;

public class HelloUnevenlyScalingWorld extends BlurpJavaProgram {

    @Override
    public void run() {

        ImageSprite world = resources.createImageSprite("hello-world.png");

        while(true) {
            double waveValue = utils.waveValue(0, 0.5, 1000);
            world.setScaleX(0.75 + waveValue);
            world.setScaleY(1.25 - waveValue);
            screen.update();
        }
    }
}
