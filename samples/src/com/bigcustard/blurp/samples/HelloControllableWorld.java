package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.java.*;

public class HelloControllableWorld extends BlurpJavaProgram {

    @Override
    public void run() {

        ImageSprite world = resources.createImageSprite("hello-world.png");

        while(true) {

            if(keyboard.isKeyPressed(Key.Left_Key)) world.x -= 2;
            if(keyboard.isKeyPressed(Key.Right_Key)) world.x += 2;
            if(keyboard.isKeyPressed(Key.Up_Key)) world.y += 2;
            if(keyboard.isKeyPressed(Key.Down_Key)) world.y -= 2;

            screen.update();
        }
    }
}
