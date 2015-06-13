package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.java.*;

public class TextSizeSample extends BlurpJavaProgram {


    @Override
    public void run() {

        double yPosition = 545;
        for(int i = 100; i > 0; i -= 10) {
            system.createTextSprite("Text with line height " + i + "px")
                 .fontSize(i)
                 .position(400, yPosition);
            screen.update();
            utils.sleep(250);

            yPosition -= i;
        }
    }
}
