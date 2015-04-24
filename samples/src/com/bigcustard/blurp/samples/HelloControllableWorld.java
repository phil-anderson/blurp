package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;

public class HelloControllableWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils) {

        ImageSprite world = blurp.imageSprite("hello-world.png");

        while(true) {

            if(keyboard.isKeyPressed(Keys.Key_Left)) world.x -= 2;
            if(keyboard.isKeyPressed(Keys.Key_Right)) world.x += 2;
            if(keyboard.isKeyPressed(Keys.Key_Up)) world.y += 2;
            if(keyboard.isKeyPressed(Keys.Key_Down)) world.y -= 2;

            blurp.blurpify();
        }
    }
}
