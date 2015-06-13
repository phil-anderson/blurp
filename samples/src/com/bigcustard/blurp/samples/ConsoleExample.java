package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.java.*;

public class ConsoleExample extends BlurpJavaProgram {

    @Override
    public void run() {

        console.println("The quick, brown fox jumped over the lazy dog.");
        screen.update();
        utils.sleep(1000);

        console.println("Pack my box with five dozen liquor jugs");
        screen.update();
        utils.sleep(1000);

        console.println("Amazingly few discotheques provide jukeboxes.");
        screen.update();
        utils.sleep(1000);

        console.println("The five boxing wizards jump quickly.");
        screen.update();
        utils.sleep(1000);

        while(true) {
            console.colour(utils.randomColour());
            console.print("Blurp Rules!!! ");
            screen.update();

            if(keyboard.isKeyJustPressed(Key.Key_Space)) {
                console.clear();
            }
        }
    }
}
