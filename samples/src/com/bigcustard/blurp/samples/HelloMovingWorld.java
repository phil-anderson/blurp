package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.java.*;

public class HelloMovingWorld extends BlurpJavaProgram {

    @Override
    public void run() {

        ImageSprite world = blurp.createImageSprite("hello-world.png");

        while(true) {

            world.moveTowards(mouse.x(), mouse.y(), 7.5);
            blurp.blurpify();
        }
    }
}
