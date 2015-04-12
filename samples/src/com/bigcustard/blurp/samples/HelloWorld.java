package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;

public class HelloWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils, Keys keys) {

        blurp.imageSprite("world.png");
    }
}
