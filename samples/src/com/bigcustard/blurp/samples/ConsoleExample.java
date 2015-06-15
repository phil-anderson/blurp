package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.java.*;

public class ConsoleExample extends BlurpJavaProgram {

    @Override
    public void run() {

        system.console.println("The quick, brown fox jumped over the lazy dog.");
        screen.update();
        system.pause(500);

        system.console.println("Pack my box with five dozen liquor jugs");
        screen.update();
        system.pause(500);

        system.console.println("Amazingly few discotheques provide jukeboxes.");
        screen.update();
        system.pause(500);

        system.console.println("The five boxing wizards jump quickly.");
        screen.update();
        system.pause(500);

        while(true) {
            system.console.colour(utils.randomColour());
            system.console.print("Blurp Rules!!! ");
            screen.update();

            if(keyboard.wasKeyJustPressed(Key.Key_Space)) {
                system.console.clear();
            }
        }
    }
}
