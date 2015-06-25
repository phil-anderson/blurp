package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.java.*;

public class HelloColourTintedWorlds extends BlurpJavaProgram {


    @Override
    public void run() {

        Image worldImage = resources.loadImage("hello-world.png");
        resources.createImageSprite(worldImage)
             .setPosition(180, 180)
             .setColour(Colours.Red);

        resources.createImageSprite(worldImage)
             .setPosition(400, 420)
             .setColour(Colours.Green);

        resources.createImageSprite(worldImage)
             .setPosition(620, 180)
             .setColour(Colours.Blue);

        screen.update();
    }
}
