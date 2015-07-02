package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.java.*;

public class HelloColourTintedWorlds extends BlurpJavaProgram {


    @Override
    public void run() {

        resources.createImageSprite("hello-world.png")
             .setPosition(180, 180)
             .setColour(Colours.Red);

        resources.createImageSprite("hello-world.png")
             .setPosition(400, 420)
             .setColour(Colours.Green);

        resources.createImageSprite("hello-world.png")
             .setPosition(620, 180)
             .setColour(Colours.Blue);

        screen.update();
    }
}
