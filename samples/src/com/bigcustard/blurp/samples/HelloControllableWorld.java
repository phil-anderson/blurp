package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class HelloControllableWorld implements IBlurpRunnable {

    @Override
    public void run(Blurp blurp, Canvas canvas, IKeyboard keyboard) {

        ImageSprite world = new ImageSprite("world.png");

        while(true) {

            if(keyboard.isKeyDown(Key.LEFT)) world.x -= 2;
            if(keyboard.isKeyDown(Key.RIGHT)) world.x += 2;
            if(keyboard.isKeyDown(Key.UP)) world.y += 2;
            if(keyboard.isKeyDown(Key.DOWN)) world.y -= 2;

            blurp.blurpify();
        }
    }
}
