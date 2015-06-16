package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.java.*;

public class HelloColourTintedWorlds extends BlurpJavaProgram {


    @Override
    public void run() {

        Image worldImage = resources.loadImage("hello-world.png");
        resources.createImageSprite(worldImage)
             .position(180, 180)
             .colour(Colours.Red);

        resources.createImageSprite(worldImage)
             .position(400, 420)
             .colour(Colours.Green);

        resources.createImageSprite(worldImage)
             .position(620, 180)
             .colour(Colours.Blue);

        screen.update();
    }
}
