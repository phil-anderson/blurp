package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;

public class HelloAlphaChangingWorld implements IBlurpRunnable {

    @Override
    public void run(Blurp blurp, Canvas canvas, IKeyboard keyboard, Utils utils) {

        ImageSprite world = new ImageSprite("world.png");

        int index = 0;

        while(true) {
            world.alpha = utils.sin(index) / 2 + 0.5;
            index += 3;

            blurp.blurpify();
        }
    }
}
