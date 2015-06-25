package com.bigcustard.blurp.samples;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import com.bigcustard.blurp.model.java.*;

public class TextExample extends BlurpJavaProgram {

    private static final String MESSAGE = "The quick, brown fox jumped over the lazy dog.";
    private static final String COLOURFUL_MESSAGE = "All colours can be specified as [Red]red[], [Green]green[] and [Blue]blue[] components";

    @Override
    public void run() {

//        system.debugMode(true);

        resources.createTextSprite("LEFT-JUSTIFIED\n" + MESSAGE)
             .setPosition(150, 500)
             .setWrap(220, Justification.AlignLeft);

        resources.createTextSprite("CENTER-JUSTIFIED\n" + MESSAGE)
             .setPosition(400, 500)
             .setWrap(220, Justification.AlignCenter);

        resources.createTextSprite("RIGHT-JUSTIFIED\n" + MESSAGE)
            .setPosition(650, 500)
            .setWrap(220, Justification.AlignRight);

        resources.createTextSprite(COLOURFUL_MESSAGE)
                                       .setPosition(400, 400)
                                       .setColourTagsEnabled(true);

        resources.createImageSprite("hello-world.png")
             .setPosition(250, 200)
             .setTransparency(0.3);

        TextSprite anchorText = resources.createTextSprite("")
                                     .setPosition(250, 200);

        TextSprite rotateAndScaleText = resources.createTextSprite("Rotate and Scale!").setPosition(600, 200);
        rotateAndScaleText.colour = Colours.DodgerBlue;

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
                anchorText.text = "Bottom-Right Handle\nBottom-Right Handle\nBottom-Right Handle";
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
                anchorText.text = "Top-Left Handle\nTop-Left Handle\nTop-Left Handle";
            } else if(frameCount == 350) {
                anchorText.handle = Handle.Right;
                anchorText.text = "Right Handle\nRight Handle\nRight Handle";
            } else if(frameCount == 400) {
                anchorText.handle = Handle.Center;
                anchorText.text = "Center Handle\nCenter Handle\nCenter Handle";
            }
            rotateAndScaleText.rotateBy(2);
            rotateAndScaleText.setScale(utils.waveValue(0.5, 2, 2500));
            frameCount = (frameCount + 1) % 450;
            screen.update();
        }
    }
}
