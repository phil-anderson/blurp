package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.java.*;

public class HelloColourTintedWorlds extends BlurpJavaProgram {


    @Override
    public void run() {

        Image worldImage = system.loadImage("hello-world.png");
        system.createImageSprite(worldImage)
             .position(180, 180)
             .colour(Colours.RED);

        system.createImageSprite(worldImage)
             .position(400, 420)
             .colour(Colours.GREEN);

        system.createImageSprite(worldImage)
             .position(620, 180)
             .colour(Colours.BLUE);

        screen.update();
    }
}
