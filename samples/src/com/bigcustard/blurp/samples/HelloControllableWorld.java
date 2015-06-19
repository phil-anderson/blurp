package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.java.*;

public class HelloControllableWorld extends BlurpJavaProgram {

    @Override
    public void run() {

        ImageSprite world = resources.createImageSprite("hello-world.png");

        while(true) {

            if(keyboard.Left.isPressed()) world.x -= 2;
            if(keyboard.Right.isPressed()) world.x += 2;
            if(keyboard.Up.isPressed()) world.y += 2;
            if(keyboard.Down.isPressed()) world.y -= 2;

            screen.update();
        }
    }
}
