package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.java.*;

public class CreationZOrderExample extends BlurpJavaProgram {

    @Override
    public void run() {

        resources.createImageSprite("hello-world.png").setPosition(275, 300);
        resources.createTextSprite("Sandwiched!").setPosition(400, 400).setFontSize(100).setColour(Colours.Crimson);
        resources.createImageSprite("hello-world.png").setPosition(525, 300);
        resources.createTextSprite("In Front!").setPosition(400, 200).setFontSize(100).setColour(Colours.Crimson);
        screen.update();
    }
}
