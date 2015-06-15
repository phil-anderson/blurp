package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.java.*;

public class LogExample extends BlurpJavaProgram {

    @Override
    public void run() {

        system.log.println("The quick, brown fox jumped over the lazy dog.");
        screen.update();
        system.pause(500);

        system.log.println("Pack my box with five dozen liquor jugs");
        screen.update();
        system.pause(500);

        system.log.println("Amazingly few discotheques provide jukeboxes.");
        screen.update();
        system.pause(500);

        system.log.println("The five boxing wizards jump quickly.");
        screen.update();
        system.pause(500);

        while(true) {
            system.log.colour(utils.randomColour());
            system.log.print("Blurp Rules!!! ");
            screen.update();

            if(keyboard.wasKeyJustPressed(Key.Key_Space)) {
                system.log.clear();
            }
        }
    }
}
