package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;

public class HelloColourTintedWorlds implements BlurpRunnable {


    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils) {

        Image worldImage = blurp.loadImage("hello-world.png");
        ImageSprite redWorld = blurp.createImageSprite(worldImage).position(180, 180);
        ImageSprite greenWorld = blurp.createImageSprite(worldImage).position(400, 420);
        ImageSprite blueWorld = blurp.createImageSprite(worldImage).position(620, 180);

        redWorld.colour = Colours.RED;
        greenWorld.colour = Colours.GREEN;
        blueWorld.colour = Colours.BLUE;

        blurp.blurpify();
    }
}
