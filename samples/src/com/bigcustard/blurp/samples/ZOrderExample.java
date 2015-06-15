package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.events.*;
import com.bigcustard.blurp.model.java.*;

public class ZOrderExample extends BlurpJavaProgram {

    @Override
    public void run() {

        ImageSprite world = resources.createImageSprite("hello-world.png")
                                  .colour(Colours.RED)
                                  .position(300, 200)
                                  .targetStyle(TargetStyle.Circle)
                                  .onMouseEnter(new SpriteEventHandler() {
                                      @Override
                                      public void handle(Sprite sprite) {

                                          sprite.pullToFront();
                                      }
                                  });

        world.copy().colour(Colours.GREEN).position(500, 200);
        world.copy().colour(Colours.BLUE).position(400, 400);

        while(screen.update()) { }
    }


}
