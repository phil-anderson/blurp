package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;

public class TextSample implements BlurpRunnable {

    private static final String MESSAGE = "The quick, brown fox jumped over the lazy dog.";

    @Override
    public void run(Blurp blurp, Screen screen, Keyboard keyboard, Utils utils, Keys keys, Colours colours) {

//        blurp.setDebugMode(true);

        blurp.textSprite("LEFT-JUSTIFIED\n" + MESSAGE, 150, 500).wrap(220, Justification.AlignLeft);
        blurp.textSprite("CENTER-JUSTIFIED\n" + MESSAGE, 400, 500).wrap(220, Justification.AlignCenter);
        blurp.textSprite("RIGHT-JUSTIFIED\n" + MESSAGE, 650, 500).wrap(220, Justification.AlignRight);

        TextSprite anchorText = blurp.textSprite("").position(250, 200);
        blurp.imageSprite("hello-world.png").position(250, 200).alpha = 0.3;

        TextSprite rotateAndScaleText = blurp.textSprite("Rotate and Scale!").position(600, 200);
        rotateAndScaleText.colour = colours.dodgerBlue;

        int frameCount = 0;
        while(true) {
            if(frameCount == 0) {
                anchorText.handle = Handle.BottomRight;
                anchorText.text = "Bottom-Left Handle\nBottom-Left Handle\nBottom-Left Handle";
            } else if(frameCount == 50) {
                anchorText.handle = Handle.Bottom;
                anchorText.text = "Bottom Handle\nBottom Handle\nBottom Handle";
            } else if(frameCount == 100) {
                anchorText.handle = Handle.BottomLeft;
                anchorText.text = "Bottom-Left Handle\nBottom-Left Handle\nBottom-Left Handle";
            } else if(frameCount == 150) {
                anchorText.handle = Handle.Left;
                anchorText.text = "Left Handle\nLeft Handle\nLeft Handle";
            } else if(frameCount == 200) {
                anchorText.handle = Handle.TopLeft;
                anchorText.text = "Top-Right Handle\nTop-Right Handle\nTop-Right Handle";
            } else if(frameCount == 250) {
                anchorText.handle = Handle.Top;
                anchorText.text = "Top Handle\nTop Handle\nTop Handle";
            } else if(frameCount == 300) {
                anchorText.handle = Handle.TopRight;
                anchorText.text = "Top-Right Handle\nTop-Right Handle\nTop-Right Handle";
            } else if(frameCount == 350) {
                anchorText.handle = Handle.Right;
                anchorText.text = "Right Handle\nRight Handle\nRight Handle";
            } else if(frameCount == 400) {
                anchorText.handle = Handle.Center;
                anchorText.text = "Center Handle\nCenter Handle\nCenter Handle";
            }
            rotateAndScaleText.rotateBy(2);
            rotateAndScaleText.scale(utils.wave(0.5, 2, 2500));
            frameCount = (frameCount + 1) % 450;
            blurp.blurpify();
        }
    }
}
