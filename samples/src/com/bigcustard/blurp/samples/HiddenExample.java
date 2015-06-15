package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.java.*;

public class HiddenExample extends BlurpJavaProgram {

    @Override
    public void run() {

        ImageSprite world = resources.createImageSprite("hello-world.png");

        while(true) {
            world.hidden = utils.timedToggle(1, 250);
            screen.update();
        }
    }
}
