package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;

public class HelloRotatingWorld extends BlurpMain {

    @Override
    public void run() {

        ImageSprite star = new ImageSprite("world.png", 400, 300);

        while(true) {
            star.rotateBy(1);
            blurpify();
        }
    }
}
