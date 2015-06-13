package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.java.*;

public class CreationZOrderExample extends BlurpJavaProgram {

    @Override
    public void run() {

        system.createImageSprite("hello-world.png").position(275, 300);
        system.createTextSprite("Sandwiched!").position(400, 400).fontSize(100).colour(Colours.CRIMSON);
        system.createImageSprite("hello-world.png").position(525, 300);
        system.createTextSprite("In Front!").position(400, 200).fontSize(100).colour(Colours.CRIMSON);
        screen.update();
    }
}
