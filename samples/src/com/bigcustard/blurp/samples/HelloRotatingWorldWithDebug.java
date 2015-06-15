package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.java.*;

public class HelloRotatingWorldWithDebug extends BlurpJavaProgram {

    @Override
    public void run() {

        system.debugMode(true);

        ImageSprite world = resources.createImageSprite("hello-world.png");

        while(true) {
            world.rotateBy(1);
            screen.update();
        }
    }
}
