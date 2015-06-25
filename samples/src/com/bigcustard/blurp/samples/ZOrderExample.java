package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.events.*;
import com.bigcustard.blurp.model.java.*;

public class ZOrderExample extends BlurpJavaProgram {

    @Override
    public void run() {

        ImageSprite world = resources.createImageSprite("hello-world.png")
                                  .setColour(Colours.Red)
                                  .setPosition(300, 200)
                                  .setTargetStyle(TargetStyle.Circle)
                                  .onMouseEnter(new SpriteEventHandler() {
                                      @Override
                                      public void handle(Sprite sprite) {

                                          sprite.pullToFront();
                                      }
                                  });

        world.copy().setColour(Colours.Green).setPosition(500, 200);
        world.copy().setColour(Colours.Blue).setPosition(400, 400);

        while(screen.update()) { }
    }


}
