package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.effects.*;

public class ConsoleExample implements BlurpRunnable {

    @Override
    public void run(Blurp blurp, Screen screen, Console console, Camera camera, Effects effects, Keyboard keyboard, Utils utils) {

        console.println("The quick, brown fox jumped over the lazy dog.");
        blurp.blurpify();
        utils.rest(1);

        console.colour(Colours.RED);
        console.println("Pack my box with five dozen liquor jugs");
        blurp.blurpify();
        utils.rest(1);

        console.colour(Colours.GREEN);
        console.println("Amazingly few discotheques provide jukeboxes.");
        blurp.blurpify();
        utils.rest(1);

        console.colour(Colours.BLUE);
        console.println("The five boxing wizards jump quickly.");
        blurp.blurpify();
        utils.rest(1);

        while(true) {
            console.colour(utils.randomColour());
            console.print("Blurp Rules!!! ");
            blurp.blurpify();

            if(keyboard.isKeyJustPressed(Key.Key_Space)) {
                console.clear();
            }
        }
    }
}
