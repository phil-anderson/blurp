package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public class HelloWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Camera camera, Effects effects, Keyboard keyboard, Utils utils) {

        blurp.createImageSprite("hello-world.png");

        blurp.blurpify();
    }
}
