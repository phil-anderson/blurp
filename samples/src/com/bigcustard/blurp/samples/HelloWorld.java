package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class HelloWorld implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Canvas canvas, Keyboard keyboard, Utils utils) {

        new ImageSprite("world.png");
    }
}
