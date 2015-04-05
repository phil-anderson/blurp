package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class HelloRotatingWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Canvas canvas, Keyboard keyboard, Utils utils) {

        ImageSprite world = new ImageSprite("world.png");

        while(true) {
            world.rotateBy(1);
            blurp.blurpify();
        }
    }
}
