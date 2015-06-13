package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.java.*;

public class HelloWorld extends BlurpJavaProgram {

    @Override
    public void run() {

        system.createImageSprite("hello-world.png");

        screen.update();
    }
}
