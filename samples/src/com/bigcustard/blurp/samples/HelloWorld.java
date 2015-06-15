package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.java.*;

public class HelloWorld extends BlurpJavaProgram {

    @Override
    public void run() {

        resources.createImageSprite("hello-world.png");

        screen.update();
    }
}
