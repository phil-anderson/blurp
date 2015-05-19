package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public class MouseExample implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Console console, Camera camera, Effects effects, Keyboard keyboard, Mouse mouse, Utils utils) {

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
