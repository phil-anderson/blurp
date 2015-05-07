package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.effects.*;

public class TextSizeSample implements BlurpRunnable {


    @Override
    public void run(Blurp blurp, Screen screen, Camera camera, Keyboard keyboard, Utils utils, Effects effects) {

        double yPosition = 545;
        for(int i = 100; i > 0; i -= 10) {
            blurp.createTextSprite("Text with line height " + i + "px")
                 .fontSize(i)
                 .position(400, yPosition);
            blurp.blurpify();
            utils.sleep(0.25);

            yPosition -= i;
        }
    }
}
