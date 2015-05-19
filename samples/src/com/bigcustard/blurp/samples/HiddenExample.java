package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public class HiddenExample implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Console console, Camera camera, Effects effects, Keyboard keyboard, Mouse mouse, Utils utils) {

        ImageSprite world = blurp.createImageSprite("hello-world.png");

        while(true) {
            world.hidden = utils.timedToggle(1, 0.25);
            blurp.blurpify();
        }
    }
}
