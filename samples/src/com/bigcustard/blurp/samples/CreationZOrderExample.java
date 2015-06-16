package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.java.*;

public class CreationZOrderExample extends BlurpJavaProgram {

    @Override
    public void run() {

        resources.createImageSprite("hello-world.png").position(275, 300);
        resources.createTextSprite("Sandwiched!").position(400, 400).fontSize(100).colour(Colours.Crimson);
        resources.createImageSprite("hello-world.png").position(525, 300);
        resources.createTextSprite("In Front!").position(400, 200).fontSize(100).colour(Colours.Crimson);
        screen.update();
    }
}
