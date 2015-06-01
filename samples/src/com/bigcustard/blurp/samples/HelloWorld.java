package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.java.*;

public class HelloWorld extends BlurpJavaProgram {

    @Override
    public void run() {

        blurp.createImageSprite("hello-world.png");

        blurp.blurpify();
    }
}
