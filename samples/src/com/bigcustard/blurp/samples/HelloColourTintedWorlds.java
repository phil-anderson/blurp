package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;

public class HelloColourTintedWorlds implements BlurpRunnable {


    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils, Keys keys, Colours colours) {

        Image worldImage = blurp.image("hello-world.png");
        ImageSprite redWorld = blurp.imageSprite(worldImage).position(180, 180);
        ImageSprite greenWorld = blurp.imageSprite(worldImage).position(400, 420);
        ImageSprite blueWorld = blurp.imageSprite(worldImage).position(620, 180);

        redWorld.colour = colours.red;
        greenWorld.colour = colours.green;
        blueWorld.colour = colours.blue;

        blurp.blurpify();
    }
}
