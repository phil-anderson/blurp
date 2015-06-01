package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.java.*;

public class HelloColourTintedWorlds extends BlurpJavaProgram {


    @Override
    public void run() {

        Image worldImage = blurp.loadImage("hello-world.png");
        blurp.createImageSprite(worldImage)
             .position(180, 180)
             .colour(Colours.RED);

        blurp.createImageSprite(worldImage)
             .position(400, 420)
             .colour(Colours.GREEN);

        blurp.createImageSprite(worldImage)
             .position(620, 180)
             .colour(Colours.BLUE);

        blurp.blurpify();
    }
}
