package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;

public class HelloControllableWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils, Keys keys) {

        ImageSprite world = blurp.imageSprite("hello-world.png");

        while(true) {

            if(keyboard.isKeyPressed(keys.LEFT)) world.x -= 2;
            if(keyboard.isKeyPressed(keys.RIGHT)) world.x += 2;
            if(keyboard.isKeyPressed(keys.UP)) world.y += 2;
            if(keyboard.isKeyPressed(keys.DOWN)) world.y -= 2;

            blurp.blurpify();
        }
    }
}
