package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class HelloWorld implements IBlurpRunnable {

    @Override
    public void run(Blurp blurp, Canvas canvas, IKeyboard keyboard) {

        new ImageSprite("world.png");
    }
}
