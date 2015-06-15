package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.java.*;

public class MouseExample extends BlurpJavaProgram {

    @Override
    public void run() {

        ImageSprite world = resources.createImageSprite("hello-world.png");

        while(true) {

            world.position(mouse.x(), mouse.y());

            if(mouse.isLeftButtonPressed()) {
                world.angle = -30;
            } else if(mouse.isRightButtonPressed()) {
                world.angle = 30;
            } else {
                world.angle = 0;
            }

            screen.update();
        }
    }
}
