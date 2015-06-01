package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.java.*;

public class HelloRotatingWorld extends BlurpJavaProgram {

    @Override
    public void run() {

        ImageSprite world = blurp.createImageSprite("hello-world.png");

        while(true) {
            world.rotateBy(1);
            blurp.blurpify();
        }
    }
}
