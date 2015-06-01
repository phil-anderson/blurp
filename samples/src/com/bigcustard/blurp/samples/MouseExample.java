package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.java.*;

public class MouseExample extends BlurpJavaProgram {

    @Override
    public void run() {

        ImageSprite world = blurp.createImageSprite("hello-world.png");

        while(true) {

            world.position(mouse.x(), mouse.y());

            if(mouse.isLeftButtonPressed()) {
                world.rotation = -30;
            } else if(mouse.isRightButtonPressed()) {
                world.rotation = 30;
            } else {
                world.rotation = 0;
            }

            blurp.blurpify();
        }
    }
}
