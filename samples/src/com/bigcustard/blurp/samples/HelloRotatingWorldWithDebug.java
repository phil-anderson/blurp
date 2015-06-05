package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.java.*;

public class HelloRotatingWorldWithDebug extends BlurpJavaProgram {

    @Override
    public void run() {

        blurp.debugMode(true);

        ImageSprite world = blurp.createImageSprite("hello-world.png");

        while(true) {
            world.rotateBy(1);
            blurp.blurpify();
        }
    }
}
