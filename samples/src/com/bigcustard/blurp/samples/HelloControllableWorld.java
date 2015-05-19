package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;

public class HelloControllableWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Console console, Camera camera, Effects effects, Keyboard keyboard, Mouse mouse, Utils utils) {

        ImageSprite world = blurp.createImageSprite("hello-world.png");

        while(true) {

            if(keyboard.isKeyPressed(Key.Key_Left)) world.x -= 2;
            if(keyboard.isKeyPressed(Key.Key_Right)) world.x += 2;
            if(keyboard.isKeyPressed(Key.Key_Up)) world.y += 2;
            if(keyboard.isKeyPressed(Key.Key_Down)) world.y -= 2;

            blurp.blurpify();
        }
    }
}
