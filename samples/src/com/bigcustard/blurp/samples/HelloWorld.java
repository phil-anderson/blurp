package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public class HelloWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Console console, Camera camera, Effects effects, Keyboard keyboard, Mouse mouse, Utils utils) {

        blurp.createImageSprite("hello-world.png");

        blurp.blurpify();
    }
}
