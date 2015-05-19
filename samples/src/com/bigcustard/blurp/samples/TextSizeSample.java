package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public class TextSizeSample implements BlurpRunnable {


    @Override
    public void run(Blurp blurp, Screen screen, Console console, Camera camera, Effects effects, Keyboard keyboard, Mouse mouse, Utils utils) {

        double yPosition = 545;
        for(int i = 100; i > 0; i -= 10) {
            blurp.createTextSprite("Text with line height " + i + "px")
                 .fontSize(i)
                 .position(400, yPosition);
            blurp.blurpify();
            utils.rest(0.25);

            yPosition -= i;
        }
    }
}
