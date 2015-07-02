package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.java.*;

public class ImageReferenceCounting extends BlurpJavaProgram {


    @Override
    public void run() {

        // Debug to check reference counting
        ImageSprite sprite1 = resources.createImageSprite("hello-world.png");
        screen.update();
        ImageSprite sprite2 = resources.createImageSprite("hello-world.png");
        screen.update();
        sprite1.image = "hello-world-copy.png";
        screen.update();
        sprite2.image = "hello-world-copy.png";
        // First image should be disposed of
        screen.update();

        sprite1.remove();
        screen.update();
        sprite2.remove();
        // Second image should be disposed of
        screen.update();
    }
}
