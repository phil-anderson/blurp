package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.java.*;

public class HiddenExample extends BlurpJavaProgram {

    @Override
    public void run() {

        final ImageSprite world = resources.createImageSprite("hello-world.png");

        timer.every(500, new Runnable() {
            @Override
            public void run() {
                world.hidden = !world.hidden;
            }
        });
        while(screen.update());
    }
}
